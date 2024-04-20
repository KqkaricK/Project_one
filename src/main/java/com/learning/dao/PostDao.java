package com.learning.dao;

import com.learning.entity.Post;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.blog.BlogForm;
import com.learning.web.post.PostsForm;


public interface PostDao {

    SimplePaginatedList getPosts(PostsForm form);

    SimplePaginatedList getTitle(BlogForm form);
    Post getPostById(Integer Id);

    void SaveOrUpdate(Post post);

    void deletePost(Integer Id);

}

