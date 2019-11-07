package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

    @Query(value = "SELECT t FROM FriendshipRequest t where t.userFrom.username = :userFrom AND t.userTo.username = :userTo AND t.status = :status")
    public List<FriendshipRequest> findByUserFromAndUserToAndStatus(@Param("userTo") String userTo, @Param("userFrom") String userFrom,  @Param("status")  String status);

    @Query(value = "SELECT t FROM FriendshipRequest t where t.userFrom.username = :userFrom AND t.userTo.username = :userTo AND ( t.status = 'pending' OR  t.status = 'accepted') ")
    public List<FriendshipRequest> findByUserFromAndUserToInPendingOrAccepted(@Param("userFrom") String userFrom, @Param("userTo") String userTo);

    @Query(value = "SELECT t FROM FriendshipRequest t where t.status = :status AND (t.userFrom.username = :username OR t.userTo.username = :username)")
    public List<FriendshipRequest> findByUserFromOrUserToAndStatus(@Param("username") String username, @Param("status")  String status);
}