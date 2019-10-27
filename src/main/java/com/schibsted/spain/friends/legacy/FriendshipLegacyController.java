package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.RelationShip;
import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.RelationShipRepository;
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
  RelationShipRepository relationShipRepository;
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
    List<User> users = userRepository.findByUserName(usernameFrom);
    if(!(users.isEmpty() || users.size() == 0))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User dont exist jet");

    loginService.sigIn(usernameFrom, password);

    if (validationsService.validateIsUserRegistered(usernameTo) == null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User dont exist");


    //RelationShip relationShip = new RelationShip(usernameFrom, usernameTo, "pending");
    //relationShipRepository.save(relationShip);

    throw new RuntimeException("not implemented yet!");
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


