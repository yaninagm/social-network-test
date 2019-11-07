package com.schibsted.spain.friends.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friendshiprequest")
public class FriendshipRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "user_from_id")
    //@Column(name = "user_from", nullable = true)
    private User userFrom;
    @ManyToOne
    @JoinColumn (name = "user_to_id")
    //@Column(name = "user_to",  nullable = true)
    private User userTo;
    @Column(name = "status", nullable = true)
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FriendshipRequest(User userFrom, User userTo, String status) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
    }

    public FriendshipRequest() {
    }


    @Override
    public String toString() {
        return "FriendshipRequest{" +
                "id=" + id +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", status='" + status + '\'' +
                '}';
    }
}
