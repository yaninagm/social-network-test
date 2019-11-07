package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ValidationsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityService securityService;

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
        if(!(userRepository.findByUsername(username).isEmpty()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exist");
    }

    public User validateUserWithPass(String username, String password){
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User or password incorrect");
        if (users.size() > 1) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        User user = users.iterator().next();
        String passHashed = securityService.hashPass(password);
        if (!passHashed.equals(user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User or password incorrect");
        return user;
    }
}
