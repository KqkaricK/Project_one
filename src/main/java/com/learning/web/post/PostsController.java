package com.learning.web.post;

import com.learning.dao.PostDao;
import com.learning.util.paginated.SimplePaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names = "PostController.command")
public class PostsController {

    @Autowired private PostDao postDao;

    @ModelAttribute("PostController.command")
    public PostsForm getCommand(){ return new PostsForm(); }

    @RequestMapping("/post/posts.html")
    public String searchUsers(
            Model model,
            @ModelAttribute("PostController.command") PostsForm command
    ){
        SimplePaginatedList list = postDao.getPosts(command);
        model.addAttribute("list", list);
        model.addAttribute("command", command);
        return "/post/posts";
    }

    @RequestMapping("/post/postDelete.html")
    public String delUser(@RequestParam(required = false) Integer Id) {
        if (Id != null) {
            postDao.deletePost(Id);
        }
        return "redirect:/post/posts.html";
    }
}
