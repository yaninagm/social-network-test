package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendShipRepository extends JpaRepository<RelationShip, Long> {

    public List<RelationShip> findByUserFrom(String userFrom);



    @Query(value = "SELECT t FROM RelationShip t where t.userFrom = :userFrom AND t.userTo = :userTo AND t.status = 'pending' ")
    public List<RelationShip> findByUserFromAndUserTo(@Param("userFrom") String userFrom, @Param("userTo") String userTo);



    @Query(value = "SELECT t FROM RelationShip t where t.status = :status")
    public List<RelationShip> findByStatus(@Param("status") String status);



}