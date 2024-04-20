package com.learning.web.post;

import com.learning.util.paginated.SimplePaginatedForm;

public class PostsForm extends SimplePaginatedForm {
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
