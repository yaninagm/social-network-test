package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendShipRepository extends JpaRepository<Friendship, Long> {

    public List<Friendship> findByUserFrom(String userFrom);

    @Query(value = "SELECT t FROM Friendship t where t.status = 'accepted' AND (t.userFrom = :userName OR t.userTo = :userName)")
    public List<Friendship> findByUserFromAccepted(String userName);

    @Query(value = "SELECT t FROM Friendship t where t.status NOT IN ('declined') AND (t.userFrom = :userName OR t.userTo = :userName)")
    public List<Friendship> findByUserFromNotDeclined(String userName);

    @Query(value = "SELECT t FROM Friendship t where t.userFrom = :userFrom AND t.userTo = :userTo")
    public List<Friendship> findByUserFromAndUserTo(@Param("userFrom") String userFrom, @Param("userTo") String userTo);

    @Query(value = "SELECT t FROM Friendship t where t.userFrom = :userFrom AND t.userTo = :userTo AND t.status = 'pending' ")
    public List<Friendship> findByUserFromAndUserToInPending(@Param("userFrom") String userFrom, @Param("userTo") String userTo);


    @Query(value = "SELECT t FROM Friendship t where t.status = :status")
    public List<Friendship> findByStatus(@Param("status") String status);



}