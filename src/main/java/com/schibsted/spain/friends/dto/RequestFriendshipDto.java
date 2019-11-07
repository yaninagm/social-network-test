package com.schibsted.spain.friends.dto;

public class RequestFriendshipDto {
    private String usernameFrom;
    private String usernameTo;
    private String status;

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestFriendshipDto(String usernameFrom, String usernameTo) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
    }

    public RequestFriendshipDto(String usernameFrom, String usernameTo, String status) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.status = status;
    }
}
