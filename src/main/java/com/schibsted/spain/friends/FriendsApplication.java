package com.schibsted.spain.friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.schibsted.spain.friends")

@SpringBootApplication
public class FriendsApplication {
  public static void main(String[] args) {
    SpringApplication.run(FriendsApplication.class, args);
  }
}
