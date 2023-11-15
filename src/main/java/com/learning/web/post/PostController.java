package com.learning.web.post;


import com.learning.entity.Post;
import com.learning.post.PostRepository;
import com.learning.util.paginated.SimplePaginatedList;
import com.learning.web.user.UsersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired private PostRepository postRepository;

    @ModelAttribute("PostController.command")
    public PostForm getCommand(){
        return new PostForm();
    }

    @RequestMapping("/blog.html")
    public String searchPost(
            Model model,
            @ModelAttribute("PostController.command") PostForm command
    ){
        SimplePaginatedList list = postRepository.getPost(command);
        model.addAttribute("list", list);
        model.addAttribute("command", command);
        return "blog";
    }

}
