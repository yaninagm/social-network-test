package com.schibsted.spain.friends.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;
    @Column (name = "username", nullable = true)
    private String userName;
    @Column (name = "password", nullable = true)
    private String password;
}
