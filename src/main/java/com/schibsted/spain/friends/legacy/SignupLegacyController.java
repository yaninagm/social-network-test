package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.dto.UserDto;
import com.schibsted.spain.friends.service.LoginService;
import com.schibsted.spain.friends.service.ValidationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/signup")
public class SignupLegacyController {

    @Autowired
    private LoginService loginService;

  @PostMapping
  void signUp(
          @RequestParam("username") String username,
          @RequestHeader("X-Password") String password
  ) throws Exception { //request handler
      UserDto userDto = new UserDto(username);
      loginService.signUp(userDto, password);
  }
}
