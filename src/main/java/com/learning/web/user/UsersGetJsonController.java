package com.learning.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dao.UserDao;
import com.learning.dto.JsonUsersDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersGetJsonController {

    private final UserDao userDao;
    private ObjectMapper objectMapper;

    public UsersGetJsonController(UserDao userDao) {this.userDao = userDao;}

    @GetMapping("/user/users.json")
    public JsonUsersDto getUserById() {
        UsersForm users = new UsersForm();
        JsonUsersDto json = new JsonUsersDto(userDao.getUsers(users).getList());
        return json;
    }
}
