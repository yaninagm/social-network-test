package com.schibsted.spain.friends.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@SpringBootApplication
public class FriendShipService {


    private Map getFriends(){
        Map<String, Object> user = new HashMap();
        ArrayList<String> friends = new ArrayList<>();
        friends.add("lala");
        user.put("johndoe", friends);
        return user;

    }

    Boolean isFriend(String solicitor, String newFriend){
        return true;
    }
}
