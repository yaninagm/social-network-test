package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.Friendship;
import com.schibsted.spain.friends.model.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query(value = "SELECT t FROM Friendship t where t.status = 'active' AND (t.userFrom = :userName OR t.userTo = :userName)")
    public List<Friendship> findByUserActive(String userName);

    @Query(value = "SELECT * FROM Friendship t where t.user_from = :userFrom AND t.user_to = :userTo AND t.status = 'pending' ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public List<Friendship> findByUserFromAndUserToInPending(@Param("userFrom") String userFrom, @Param("userTo") String userTo);


}