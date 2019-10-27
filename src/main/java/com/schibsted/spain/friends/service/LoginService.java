package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Validation;
import java.util.List;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;



    public  void signUp(String username, String password){
        System.out.println("[method:signUp][userName: "+ username + " ] [password: "+password + " ]");

        //TODO hashar password
        User newUser = new User(username, password);
        userRepository.save(newUser);
    }

    public  void signIn(String username, String password){
        System.out.println("[method:signIn][userName: "+ username + " ] [password: "+password + " ]");

        List<User> users = userRepository.findByUserName(username);
        if(users.isEmpty() || users.size() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User dont exist");

        for (User user : users){
            if(!Objects.equals(user.getPassword(), password))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Password");
        }
    }


}
