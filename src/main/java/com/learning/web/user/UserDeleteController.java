package com.learning.web.user;

import com.learning.dao.UserDao;
import com.learning.dto.JsonMessageDto;
import com.learning.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserDeleteController {

    private final UserDao userDao;

    public UserDeleteController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/user/deleteUser.json")
    @ResponseBody
    public JsonMessageDto delete(@RequestBody Integer userId)
    {
        JsonMessageDto json =  new JsonMessageDto();

        if (userId!=null){
            userDao.deleteUser(userId);
        }
        return json;
    }
}
