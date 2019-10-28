package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

    @Query(value = "SELECT t FROM FriendshipRequest t where t.status = 'accepted' AND (t.userFrom = :userName OR t.userTo = :userName)")
    public List<FriendshipRequest> findByUserFromAccepted(String userName);

    @Query(value = "SELECT t FROM FriendshipRequest t where t.status = 'declined' AND (t.userFrom = :userName OR t.userTo = :userName)")
    public List<FriendshipRequest> findByUserFromDeclined(String userName);

    @Query(value = "SELECT t FROM FriendshipRequest t where t.userFrom = :userFrom AND t.userTo = :userTo")
    public List<FriendshipRequest> findByUserFromAndUserTo(@Param("userFrom") String userFrom, @Param("userTo") String userTo);

    @Query(value = "SELECT t FROM FriendshipRequest t where t.userFrom = :userFrom AND t.userTo = :userTo AND t.status = 'pending' ")
    public List<FriendshipRequest> findByUserFromAndUserToInPending(@Param("userFrom") String userFrom, @Param("userTo") String userTo);




}