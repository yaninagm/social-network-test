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

        for (FriendshipRequest friendshipRequest : relations){
            if(friendshipRequest.getStatus().equals(Constants.STATUS_ACCEPTED)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already friends");
            }
            friendshipRequest.setStatus(Constants.STATUS_ACCEPTED);
            friendshipRequest.setDateLastModified(new Date());
            friendshipRequestRepository.save(friendshipRequest);
            Friendship friendship = new Friendship(usernameTo, usernameFrom, "active");
            friendshipRepository.save(friendship);
            return friendshipRequest;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
    }

    public FriendshipRequest declineFriendship(String usernameFrom, String usernameTo) {
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserTo(usernameTo, usernameFrom);
        for (FriendshipRequest friendshipRequest : relations) {
            if (!friendshipRequest.getStatus().equals(Constants.STATUS_PENDING)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't declined request");
            }
            friendshipRequest.setStatus(Constants.STATUS_DECLINED);
            friendshipRequestRepository.save(friendshipRequest);

            List<Friendship> friendships = friendshipRepository.findByUserFromAndUserToInPending(usernameFrom, usernameTo);
            for (Friendship friendship : friendships) {
                friendship.setStatus("cancelled");
                friendshipRepository.save(friendship);
            }

            return null;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
    }


    //All request are declined => BAD REQUEST
    public ArrayList <String> getPendingRelationshipRequest(String username){

        List<FriendshipRequest> relationshipRequest = friendshipRequestRepository.findByUserFromAndStatus(username, Constants.STATUS_ACCEPTED);

        ArrayList <String> friends = new ArrayList<>();
        if (relationshipRequest.size() <= 0){
            List<FriendshipRequest> relationshipsNotDeclined = friendshipRequestRepository.findByUserFromAndStatus(username, Constants.STATUS_DECLINED);
            if (relationshipsNotDeclined.size() > 0) {
                return friends;
            }
            //All request are declined
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't friends");
        }
        return friends;
    }

    public ArrayList <String> getActiveRelationship(String username){
        List<Friendship> relationship = friendshipRepository.findByUserActive(username);
        ArrayList <String> friends = new ArrayList<>();
        for (Friendship relationship1 : relationship) {
            if (relationship1.getUserTo().equals(username)) {
                friends.add(relationship1.getUserFrom());
            }else {
                friends.add(relationship1.getUserTo());
            }
        }
        return friends;
    }

}
