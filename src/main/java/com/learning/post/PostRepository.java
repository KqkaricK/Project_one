package com.learning.post;

import com.learning.entity.Post;
import com.learning.entity.User;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.post.PostForm;


public interface PostRepository  {

    SimplePaginatedList getPost(PostForm form);

    Post getPostById(Integer Id);

    void saveOrUpdate(Post post);

}

