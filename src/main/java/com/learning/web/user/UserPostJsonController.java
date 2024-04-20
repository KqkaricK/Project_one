package com.learning.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dao.UserDao;
import com.learning.dto.JsonMessageDto;
import com.learning.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserPostJsonController {

    private final UserEditValidator userEditValidator;
    private final UserDao userDao;
    private ObjectMapper objectMapper;

    public UserPostJsonController(UserDao userDao, ObjectMapper objectMapper, UserEditValidator userEditValidator) {this.userDao = userDao;
        this.objectMapper = objectMapper;
        this.userEditValidator = userEditValidator;
    }

    @PostMapping("/user/postUser.json")
    @ResponseBody
    public JsonMessageDto save(@RequestBody  User user, BindingResult error )
    {
        JsonMessageDto json =  new JsonMessageDto();
        userEditValidator.validate(user, error);
        if (!error.hasErrors()) {
            if(user.getUserId()==0){
                json.setMessage("Добавленно");
            }else{
                json.setMessage("Исполнено");
            }
            userDao.saveOrUpdate(user);
            json.setId(String.valueOf(user.getUserId()));
        } else{
          error.getAllErrors().stream().forEach(e -> {json.getErrors().add(e.getDefaultMessage());json.getErrorCode().add(e.getCode());});
        }
        return json;
    }
}
