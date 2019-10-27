package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.Friendship;
import com.schibsted.spain.friends.repository.FriendShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FriendShipService {
    @Autowired
    FriendShipRepository friendShipRepository;

    public Friendship requestFriendship(String usernameFrom, String usernameTo){
        List<Friendship> relation = friendShipRepository.findByUserFromAndUserToInPending(usernameFrom, usernameTo);
        if(!relation.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Yo have request pending");
        Friendship friendship = new Friendship(usernameFrom, usernameTo, "pending");
        friendShipRepository.save(friendship);
        return friendship;
    }

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
