package com.learning.web.post;



import com.learning.entity.Post;
import com.learning.entity.User;
import com.learning.post.PostRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository("postEditValidator")
public class PostEditValidator implements Validator {
    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Post.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if (!user.isAdmin())
            errors.reject("name", "Доступно для редактирования только администратору");
        Post post = (Post)o;
        if (StringUtils.isBlank(post.getTitle()))
            errors.reject("title", "Заголовок не может быть пустым!");
        if (StringUtils.isBlank(post.getAnons()))
            errors.reject("anons", "Анонс не может быть пустым!");
        if (StringUtils.isBlank(post.getFul_text()))
            errors.reject("ful_text", "Текст не может быть пустым!");
    }
}
