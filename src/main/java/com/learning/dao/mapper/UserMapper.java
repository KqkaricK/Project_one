package com.learning.dao.mapper;

import com.learning.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setUserRole(rs.getString("role"));

        return user;
    }
}
