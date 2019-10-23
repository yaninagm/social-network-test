package com.schibsted.spain.friends.legacy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController {

  @PostMapping
  void signUp(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    throw new RuntimeException("not implemented yet!");
  }
}
