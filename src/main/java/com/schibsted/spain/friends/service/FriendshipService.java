package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.config.Constants;
import com.schibsted.spain.friends.dto.RequestFriendshipDto;
import com.schibsted.spain.friends.model.FriendshipRequest;
import com.schibsted.spain.friends.repository.FriendshipRequestRepository;
import com.schibsted.spain.friends.repository.UserRepository;
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
    ValidationsService validationsService;
    @Autowired
    UserRepository userRepository;

    public FriendshipRequest requestFriendship(RequestFriendshipDto requestFriendshipDto, String password){
        validationsService.validateUserWithPass(requestFriendshipDto.getUsernameFrom(), password);
        if(userRepository.findByUsername(requestFriendshipDto.getUsernameTo()).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provider usernameTo doesn't exist");
        List<FriendshipRequest> relation = friendshipRequestRepository.findByUserFromAndUserToInPendingOrAccepted(
                requestFriendshipDto.getUsernameFrom(),
                requestFriendshipDto.getUsernameTo());

        if(!relation.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request already exist");
        FriendshipRequest friendshipRequest = new FriendshipRequest(requestFriendshipDto.getUsernameFrom(), requestFriendshipDto.getUsernameTo(), Constants.STATUS_PENDING );
        friendshipRequestRepository.save(friendshipRequest);
        return friendshipRequest;
    }

    public FriendshipRequest editStatusFriendship(RequestFriendshipDto requestFriendshipDto, String password) {
        validationsService.validateUserWithPass(requestFriendshipDto.getUsernameFrom(), password);
        List<FriendshipRequest> relations = friendshipRequestRepository.findByUserFromAndUserToAndStatus(
                requestFriendshipDto.getUsernameFrom(),
                requestFriendshipDto.getUsernameTo(),
                Constants.STATUS_PENDING);

        if (relations.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You haven't any request");
        FriendshipRequest friendshipRequest = relations.iterator().next();
        friendshipRequest.setStatus(requestFriendshipDto.getStatus());
        friendshipRequestRepository.save(friendshipRequest);
        return friendshipRequest;
    }

    public ArrayList <String> getAcceptFriendshipRequest(String username, String password){
        validationsService.validateUserWithPass(username, password);
        List<FriendshipRequest> friendshipRequests = friendshipRequestRepository.findByUserFromOrUserToAndStatus(username, Constants.STATUS_ACCEPTED);
        ArrayList <String> friends = new ArrayList<>();
        for (FriendshipRequest friendshipRequest : friendshipRequests) {
            if (friendshipRequest.getUserTo().equals(username)) {
                friends.add(friendshipRequest.getUserFrom());
            }else {
                friends.add(friendshipRequest.getUserTo());
            }
        }
        return friends;
    }

}
