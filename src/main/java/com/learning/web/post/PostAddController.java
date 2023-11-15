package com.learning.web.post;

import com.learning.entity.Post;
import com.learning.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostAddController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostEditValidator postEditValidator;

    @RequestMapping(value = "/blog_add.html", method = RequestMethod.GET)
    public String onGet(
            Model model,
            @RequestParam(required = false) Integer Id
    ) {

        Post command = postRepository.getPostById(Id);
        if (command==null){
            command = new Post();
        }
        model.addAttribute("command", command);
        return "blog_add";
    }

    @RequestMapping(value = "/blog_add.html", method = RequestMethod.POST, params = "!_cancel")
    public String onPost(
            Model model,
            @ModelAttribute("command") Post command,
            BindingResult result){

        postEditValidator.validate(command, result);
        if (!result.hasErrors()) {
            postRepository.saveOrUpdate(command);
        }else {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("command", command);
            return "blog_add.html";
        }
        return "redirect:blog_add.html?Id=" + command.getId();
    }

    @RequestMapping(value = "/blog_add.html", method = RequestMethod.POST, params = "_cancel")
    public String onCancel() {
        return "redirect:blog_add.html";
    }
}
