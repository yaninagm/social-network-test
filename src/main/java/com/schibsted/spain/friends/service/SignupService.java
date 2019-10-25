package com.schibsted.spain.friends.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;

public class SignupService {
    public static void validate(String username, String password) throws Exception {
        System.out.println(">>>>>> LALALALL: "+ username + ">> "+password);

        Map<String, String> sampleUsers = new HashMap();
        sampleUsers.put("johndoe", "j12345678");
        sampleUsers.put("roseanne", "r3456789");
        sampleUsers.put("peter", "p4567890");
        sampleUsers.put("jessica", "j5678901");
        sampleUsers.put("robert", "r0123456");


        if(sampleUsers.get(username) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exist");

        if(!password.equals(sampleUsers.get(username)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect pass");

    }
}
