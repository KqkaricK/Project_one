package com.learning.dto;


import com.learning.entity.User;

public class UserDto {

    private final int userId;
    private final String username;

    public UserDto(User user) {
        userId = user.getUserId();
        username = user.getUsername();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

}