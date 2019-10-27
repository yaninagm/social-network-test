package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ValidationsService {

    @Autowired
    private UserRepository userRepository;

    public User validateIsUserRegistered(String username){
        List<User> users = userRepository.findByUserName(username);
        if(users.isEmpty() || users.size() == 0)
            return null;
        return users.get(0);
    }
    public void validateUserName(String username){
        if (!username.matches("[a-zA-Z0-9]+") )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and password must be alphanumeric");
        if (!(username.length() >= 5  && username.length() < 10))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must be into 5 to 10 characters");

    }
    public void validatePassword(String password){
        if (!password.matches("[a-zA-Z0-9]+"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and password must be alphanumeric");
        if (!(password.length() >= 8  && password.length() < 12))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be into 8 to 10 characters ");
    }

    public void validateIfUserExist(String username){
        List<User> users = userRepository.findByUserName(username);
        if(!(users.isEmpty() || users.size() == 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist yet");
    }

}
