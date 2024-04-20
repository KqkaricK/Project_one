package com.learning.dto;


import com.learning.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUsersDto {

    private final List users;

    public List getUsers() {
        return users;
    }

    public JsonUsersDto(List <User> users) {
        List<UserDto> usersPublic = new ArrayList<>();
        for (User user : users){
            UserDto userDto = new UserDto(user);
            usersPublic.add(userDto);
        }
        this.users = Collections.unmodifiableList(usersPublic);
    }
}