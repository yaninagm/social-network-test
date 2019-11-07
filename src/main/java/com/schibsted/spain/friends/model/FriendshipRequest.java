package com.schibsted.spain.friends.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friendshiprequest")
public class FriendshipRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userFrom;
    private String userTo;
    private String status;


    @Column(name = "user_from", nullable = true)
    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    @Column(name = "user_to", nullable = true)
    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FriendshipRequest() {
    }

    public FriendshipRequest(String userFrom, String userTo, String status) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
    }

    @Override
    public String toString() {
        return "FriendshipRequest{" +
                "id=" + id +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
