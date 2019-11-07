package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUsername(String username);
}