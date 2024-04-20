package com.learning.web.blog;

import com.learning.dao.PostDao;
import com.learning.util.paginated.SimplePaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names = "BlogController.command")
public class BlogController {

    @Autowired private PostDao postDao;

    @ModelAttribute("BlogController.command")
    public BlogForm getCommand(){
        return new BlogForm();
    }

    @RequestMapping("/blog.html")
    public String DisplayPosts(
            Model model,
            @ModelAttribute("BlogController.command") BlogForm command
    ){
        SimplePaginatedList list = postDao.getTitle(command);
        model.addAttribute("list", list);
        model.addAttribute("command", command);
        return "blog";
    }

    @RequestMapping("/blog/post№{id}.html")
    public String OpenPosts(
            Model model,
            @ModelAttribute("BlogController.command") BlogForm selectPost, @PathVariable int id)
    {
        model.addAttribute("selectPost", postDao.getPostById(id));
        return "post/post";
    }

}
