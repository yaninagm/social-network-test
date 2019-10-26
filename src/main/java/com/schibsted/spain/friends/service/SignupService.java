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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    /*
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
*/
    public  void validate(String username, String password)  throws Exception {
        System.out.println(">>>>>> LALALALL: "+ username + ">> "+password);

        Map<String, String> sampleUsers = new HashMap();
        sampleUsers.put("johndoe", "j12345678");
        sampleUsers.put("roseanne", "r3456789");
        sampleUsers.put("peter", "p4567890");
        sampleUsers.put("jessica", "j5678901");
        sampleUsers.put("robert", "r0123456");
        userRepository.findAll();
        User uno = userRepository.getOne(1L);
        List<User> users = userRepository.findByUserName(username);
        //Optional<User> dos = userRepository.findByName(username);
        //System.out.println("1>>>>>> "+dos.get().getUserName());


        if(sampleUsers.get(username) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exist");

        if(!password.equals(sampleUsers.get(username)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect pass");

    }
}
