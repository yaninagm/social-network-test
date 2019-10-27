package com.schibsted.spain.friends.model;

import javax.persistence.*;

@Entity
@Table(name = "relationship")
public class RelationShip {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String friendFrom;
    private String friendTo;
    private String status;

    @Column(name = "friend_from", nullable = true)
    public String getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(String friendFrom) {
        this.friendFrom = friendFrom;
    }

    @Column(name = "friend_to", nullable = true)
    public String getFriendTo() {
        return friendTo;
    }

    public void setFriendTo(String friendTo) {
        this.friendTo = friendTo;
    }

    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RelationShip(String friendFrom, String friendTo, String status) {
        this.friendFrom = friendFrom;
        this.friendTo = friendTo;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RelationShip{" +
                "friendFrom='" + friendFrom + '\'' +
                ", friendTo='" + friendTo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
