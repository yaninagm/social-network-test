package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends JpaRepository<RelationShip, Long> {



}