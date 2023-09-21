package com.learning.web.user;

import com.learning.dao.UserDao;
import com.learning.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by ulyanov on 30.09.16.
 */
@Repository("userEditValidator")
public class UserEditValidator implements Validator {
    @Autowired private UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User)authentication.getPrincipal();
        if (!user1.isAdmin())
            errors.reject("name", "Доступно для редактирования только администратору");

        User user = (User)o;
        if (StringUtils.isBlank(user.getUsername()))
            errors.reject("username", "Имя пользователя не модет быть пустым!");
        else{
            if (!userDao.isUniqueUser(user)){
                errors.reject("username", "Такое имя уже существует!");
            }
        }
        if (StringUtils.isBlank(user.getPassword()))
            errors.reject("username", "Пароль пользователя не модет быть пустым!");
        if (StringUtils.isBlank(user.getEmail()))
            errors.reject("username", "Email пользователя не модет быть пустым!");
    }
}
