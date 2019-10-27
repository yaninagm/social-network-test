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

import java.util.List;

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


    List<RelationShip> relation = friendShipRepository.findByUserFromAndUserTo(usernameFrom, usernameTo);

    if(!relation.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request pending");

    RelationShip relationShip = new RelationShip(usernameFrom, usernameTo, "pending");
    System.out.println(">>>>>>>"+ relationShip);

    friendShipRepository.save(relationShip);

  }

  @PostMapping("/accept")
  void acceptFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    throw new RuntimeException("not implemented yet!");
  }

  @PostMapping("/decline")
  void declineFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    throw new RuntimeException("not implemented yet!");
  }

  @GetMapping("/list")
  Object listFriends(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    throw new RuntimeException("not implemented yet!");
  }

}


