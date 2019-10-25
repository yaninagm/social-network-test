package com.schibsted.spain.friends.repository;


import com.schibsted.spain.friends.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUserName(String userName);

    User findById(long id);
}