package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.config.Constants;
import com.schibsted.spain.friends.model.Friendship;
import com.schibsted.spain.friends.model.FriendshipRequest;
import com.schibsted.spain.friends.repository.FriendShipRequestRepository;
import com.schibsted.spain.friends.repository.FriendShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@Service
public class FriendShipService {
    @Autowired
    FriendShipRequestRepository friendshipRequestRepository;
    @Autowired
    FriendShipRepository friendshipRepository;

    public FriendshipRequest requestFriendship(String usernameFrom, String usernameTo){
        List<FriendshipRequest> relation = friendshipRequestRepository.findByUserFromAndUserToInPending(usernameFrom, usernameTo);
        if(!relation.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Yo have request pending");

        FriendshipRequest friendshipRequest = new FriendshipRequest(usernameFrom, usernameTo, "pending", new Date(),  new Date());
        friendshipRequestRepository.save(friendshipRequest);
        return friendshipRequest;
    }

    public FriendshipRequest acceptFriendship(String usernameFrom, String usernameTo){
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);
        for (FriendshipRequest friendshipRequest : relations){
            if(Objects.equals(friendshipRequest.getStatus(), Constants.STATUS_ACCEPTED)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already friends");
            }
            friendshipRequest.setStatus(Constants.STATUS_ACCEPTED);
            friendshipRequest.setDateLastModified(new Date());
            friendshipRequestRepository.save(friendshipRequest);
            Friendship friendship = new Friendship(usernameFrom,usernameTo, "active");
            friendshipRepository.save(friendship);
            return null;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
    }

    public FriendshipRequest declineFriendship(String usernameFrom, String usernameTo) {
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);
        for (FriendshipRequest friendshipRequest : relations) {
            if (Objects.equals(friendshipRequest.getStatus(), Constants.STATUS_ACCEPTED) || Objects.equals(friendshipRequest.getStatus(), Constants.STATUS_DECLINE)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't declined request");
            }
            friendshipRequest.setStatus(Constants.STATUS_DECLINE);
            friendshipRequestRepository.save(friendshipRequest);
            return null;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
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
