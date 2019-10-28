package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.config.Constants;
import com.schibsted.spain.friends.model.Friendship;
import com.schibsted.spain.friends.model.FriendshipRequest;
import com.schibsted.spain.friends.repository.FriendshipRequestRepository;
import com.schibsted.spain.friends.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@Service
public class FriendshipService {
    @Autowired
    FriendshipRequestRepository friendshipRequestRepository;
    @Autowired
    FriendshipRepository friendshipRepository;

    public FriendshipRequest requestFriendship(String usernameFrom, String usernameTo){
        List<FriendshipRequest> relation = friendshipRequestRepository.findByUserFromAndUserToInPendingOrAccepted(usernameFrom, usernameTo);
        if(!relation.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't request in status pending");

        Date newDate = new Date();
        FriendshipRequest friendshipRequest = new FriendshipRequest(usernameFrom, usernameTo, Constants.STATUS_PENDING , newDate,  newDate);
        friendshipRequestRepository.save(friendshipRequest);
        return friendshipRequest;
    }

    public FriendshipRequest acceptFriendship(String usernameFrom, String usernameTo){
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserToAndStatus(usernameTo, usernameFrom, Constants.STATUS_PENDING);

        if (relations.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request for accept");

        FriendshipRequest friendshipRequest = relations.iterator().next();
        friendshipRequest.setStatus(Constants.STATUS_ACCEPTED);
        friendshipRequest.setDateLastModified(new Date());
        friendshipRequestRepository.save(friendshipRequest);

        Friendship friendship = new Friendship(usernameTo, usernameFrom, "active");
        friendshipRepository.save(friendship);

        return friendshipRequest;
    }

    public FriendshipRequest declineFriendship(String usernameFrom, String usernameTo) {
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserToAndStatus(usernameTo, usernameFrom, Constants.STATUS_PENDING);
        if (relations.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");

        FriendshipRequest friendshipRequest = relations.iterator().next();
        friendshipRequest.setStatus(Constants.STATUS_DECLINED);
        friendshipRequest.setDateLastModified(new Date());
        friendshipRequestRepository.save(friendshipRequest);

        return friendshipRequest;
    }

    public ArrayList <String> getActiveRelationship(String username){
        List<FriendshipRequest> relationships = friendshipRequestRepository.findByUserFromOrUserToAndStatus(username, username, Constants.STATUS_ACCEPTED);
        ArrayList <String> friends = new ArrayList<>();
        for (FriendshipRequest relationship1 : relationships) {
            if (relationship1.getUserTo().equals(username)) {
                friends.add(relationship1.getUserFrom());
            }else {
                friends.add(relationship1.getUserTo());
            }
        }
        return friends;
    }

}
