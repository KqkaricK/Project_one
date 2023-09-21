package com.learning.dao;

import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.user.UsersForm;
import com.learning.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDao extends UserDetailsService {
    SimplePaginatedList getUsers(UsersForm form);
    User getUserByUserId(Integer userId);
    User getUserByEmail(String email);
    void saveOrUpdate(User user);
    void deleteUser(Integer userId);
    boolean isUniqueUser(User user);
}
