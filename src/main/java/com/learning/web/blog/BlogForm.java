package com.learning.web.blog;

import com.learning.util.paginated.SimplePaginatedForm;

public class BlogForm extends SimplePaginatedForm {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
