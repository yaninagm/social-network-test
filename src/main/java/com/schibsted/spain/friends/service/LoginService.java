package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.dto.UserDto;
import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    ValidationsService validation;

    public void signUp(UserDto userDto, String password) {
        validation.validateIfUserExist(userDto.getUsername());
        validation.validateUserName(userDto.getUsername());
        validation.validatePassword(password);
        String hashedPass = securityService.hashPass(password);
        User newUser = new User(userDto.getUsername(), hashedPass);
        userRepository.save(newUser);
    }
}
