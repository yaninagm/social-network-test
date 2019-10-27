package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.Friendship;
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
  FriendShipService friendshipService;
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
    if(userRepository.findByUserName(usernameTo).isEmpty())
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User to invited doesn't exist");

    friendshipService.requestFriendship(usernameFrom, usernameTo);
  }

  @PostMapping("/accept")
  void acceptFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    System.out.println("[method:acceptFriendship] [usernameFrom: "+usernameFrom+"] [usernameTo: "+usernameTo +"]");

    loginService.signIn(usernameFrom, password);

    List<Friendship> relations = friendShipRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);

    if (relations.size() > 0) {
      for (Friendship friendship : relations){
        if(Objects.equals(friendship.getStatus(), "accepted")){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already friends");
        }
        friendship.setStatus("accepted");
        friendShipRepository.save(friendship);
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

    List<Friendship> relations = friendShipRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);

    if (relations.size() > 0) {
      for (Friendship friendship : relations){
        if(Objects.equals(friendship.getStatus(), "accepted") || Objects.equals(friendship.getStatus(), "declined")){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't declined request");
        }
        friendship.setStatus("declined");
        friendShipRepository.save(friendship);
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
    List<Friendship> relationships = friendShipRepository.findByUserFromAccepted(username);

    ArrayList <String> friends = new ArrayList<>();


    if (relationships.size() <= 0){
      List<Friendship> relationshipsNotDeclined = friendShipRepository.findByUserFromNotDeclined(username);
      if (relationshipsNotDeclined.size() == 0) {
        return friends;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't friends");
    }


    for (Friendship relationship : relationships) {
      if (Objects.equals(relationship.getUserTo(), username)) {
        friends.add(relationship.getUserFrom());
      }else {
        friends.add(relationship.getUserTo());
      }
    }

    return friends;
  }

}


