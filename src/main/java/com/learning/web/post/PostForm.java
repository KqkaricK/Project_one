package com.learning.web.post;

import com.learning.util.paginated.SimplePaginatedForm;

/**
 * Created by ulyanov on 29.09.16.
 */
public class PostForm extends SimplePaginatedForm {
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
