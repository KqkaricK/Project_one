package com.learning.web.user;

import com.learning.util.paginated.SimplePaginatedForm;

/**
 * Created by ulyanov on 29.09.16.
 */
public class UsersForm extends SimplePaginatedForm {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
