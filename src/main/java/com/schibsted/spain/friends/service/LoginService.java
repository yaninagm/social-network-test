package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public  void signUp(String username, String password){
        System.out.println("[method:signUp][userName: "+ username + "]");
        //TODO hashar password
        User newUser = new User(username, password);
        userRepository.save(newUser);
    }

    public  void signIn(String username, String password){
        System.out.println("[method:signIn][userName: "+ username + " ] [password: "+password + " ]");

        List<User> users = userRepository.findByUserName(username);
        if(users.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User or password incorrect");
        if (users.size() > 1 )
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate username");
        User user = users.iterator().next();
        if(!password.equals(user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User or password incorrect");

    }


}
