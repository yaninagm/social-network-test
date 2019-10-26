package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    public  void validateUserName(String username, String password)  throws Exception {
        System.out.println("[method:validateUserName][userName: "+ username + " ] [password: "+password + " ]");

        List<User> users = userRepository.findByUserName(username);

        // Valida que el usuario sea unico
        if(!(users.isEmpty() || users.size() == 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist jet");
        if (!username.matches("[a-zA-Z0-9]+") || !password.matches("[a-zA-Z0-9]+"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and password must be alphanumeric");
        // Valida condiciones del nombre del usuario
        if (!(username.length() >= 5  && username.length() < 10))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must be into 5 to 10 characters");
        // Valida condiciones del nombre del usuario
        if (!(password.length() >= 8  && password.length() < 12))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be into 8 to 10 characters ");

        //TODO hashar password
        User newUser = new User(username, password);
        userRepository.save(newUser);

    }

}
