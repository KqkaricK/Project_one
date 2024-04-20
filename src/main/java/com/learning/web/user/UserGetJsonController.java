package com.learning.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dao.UserDao;
import com.learning.dto.JsonUserDto;
import com.learning.entity.User;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserGetJsonController {

    private final UserDao userDao;
    private ObjectMapper objectMapper;

    public UserGetJsonController(UserDao userDao) {this.userDao = userDao;}

    @GetMapping("/user/getUser.json")
    public JsonUserDto getUserById(@RequestParam Integer userId) {

        JsonUserDto json = new JsonUserDto();

        json.setUserId(userId);
        json.setUsername(userDao.getUserByUserId(userId).getUsername());
        json.setPassword(userDao.getUserByUserId(userId).getPassword());
        json.setEmail(userDao.getUserByUserId(userId).getEmail());
        json.setUserRole(userDao.getUserByUserId(userId).getUserRole());
        json.setEnabled(userDao.getUserByUserId(userId).isEnabled());

        return json;
    }
}
