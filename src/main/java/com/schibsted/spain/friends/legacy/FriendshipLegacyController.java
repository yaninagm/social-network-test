package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.RelationShip;
import com.schibsted.spain.friends.repository.FriendShipRepository;
import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.service.FriendShipService;
import com.schibsted.spain.friends.service.LoginService;
import com.schibsted.spain.friends.service.ValidationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/friendship")
public class FriendshipLegacyController {
  @Autowired
  FriendShipService friendShipService;
  @Autowired
  UserRepository userRepository;
  @Autowired
  FriendShipRepository friendShipRepository;
  @Autowired
  ValidationsService validationsService;
  @Autowired
  LoginService loginService;

  @PostMapping("/request")
  void requestFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    System.out.println("[method:requestFriendship] [usernameFrom: "+usernameFrom+"] [usernameTo: "+usernameTo +"]");

    loginService.signIn(usernameFrom, password);

    if (validationsService.validateIsUserRegistered(usernameTo) == null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User to invited dont exist");

    List<RelationShip> relation = friendShipRepository.findByUserFromAndUserToInPending(usernameFrom, usernameTo);

    if(!relation.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request pending");

    RelationShip relationShip = new RelationShip(usernameFrom, usernameTo, "pending");
    friendShipRepository.save(relationShip);
  }

  @PostMapping("/accept")
  void acceptFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    System.out.println("[method:acceptFriendship] [usernameFrom: "+usernameFrom+"] [usernameTo: "+usernameTo +"]");

    loginService.signIn(usernameFrom, password);

    List<RelationShip> relations = friendShipRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);

    System.out.println("FOR: from: "+usernameFrom + " to: "+ usernameTo + " relation: "+relations );
    if (relations.size() > 0) {
      for (RelationShip relationShip: relations){
        if(Objects.equals(relationShip.getStatus(), "accepted")){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already friends");
        }
        relationShip.setStatus("accepted");
        friendShipRepository.save(relationShip);
        return;
      }
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
  }

  @PostMapping("/decline")
  void declineFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    System.out.println("[method:acceptFriendship] [usernameFrom: "+usernameFrom+"] [usernameTo: "+usernameTo +"]");

    loginService.signIn(usernameFrom, password);

    List<RelationShip> relations = friendShipRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);

    System.out.println("FOR: from: "+usernameFrom + " to: "+ usernameTo + " relation: "+relations );
    if (relations.size() > 0) {
      for (RelationShip relationShip: relations){
        if(Objects.equals(relationShip.getStatus(), "accepted") || Objects.equals(relationShip.getStatus(), "declined")){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't declined request");
        }
        relationShip.setStatus("declined");
        friendShipRepository.save(relationShip);
        return;
      }
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
  }

  @GetMapping("/list")
  Object listFriends(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    System.out.println("[method:listFriends] [username: "+username+"]");
    loginService.signIn(username, password);
    List<RelationShip> relationships = friendShipRepository.findByUserFromAccepted(username);

    ArrayList <String> friends = new ArrayList<>();


    if (relationships.size() <= 0){
      List<RelationShip> relationshipsNotDeclined = friendShipRepository.findByUserFromNotDeclined(username);
      if (relationshipsNotDeclined.size() == 0) {
        return friends;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't friends");
    }


    for (RelationShip relationship : relationships) {
      if (Objects.equals(relationship.getUserTo(), username)) {
        friends.add(relationship.getUserFrom());
      }else {
        friends.add(relationship.getUserTo());
      }
    }

    return friends;
  }

}


