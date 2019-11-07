package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.config.Constants;
import com.schibsted.spain.friends.dto.RequestFriendshipDto;
import com.schibsted.spain.friends.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/friendship")
public class FriendshipLegacyController {
  @Autowired
  FriendshipService friendshipService;

  @PostMapping("/request")
  void requestFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    RequestFriendshipDto requestFriendshipDto = new RequestFriendshipDto(usernameFrom, usernameTo);
    friendshipService.requestFriendship(requestFriendshipDto, password);
  }

  @PostMapping("/accept")
  void acceptFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    RequestFriendshipDto requestFriendshipDto = new RequestFriendshipDto(usernameFrom, usernameTo, Constants.STATUS_ACCEPTED);
    friendshipService.editStatusFriendship(requestFriendshipDto, password);
  }

  @PostMapping("/decline")
  void declineFriendship(
      @RequestParam("usernameFrom") String usernameFrom,
      @RequestParam("usernameTo") String usernameTo,
      @RequestHeader("X-Password") String password
  ) {
    RequestFriendshipDto requestFriendshipDto = new RequestFriendshipDto(usernameFrom, usernameTo, Constants.STATUS_DECLINED);
    friendshipService.editStatusFriendship(requestFriendshipDto, password);
  }

  @GetMapping("/list")
  Object listFriends(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    ArrayList <String> friends = friendshipService.getAcceptFriendshipRequest(username, password);
    return friends;
  }

}


