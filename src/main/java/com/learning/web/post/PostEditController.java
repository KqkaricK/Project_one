package com.learning.web.post;

import com.learning.dao.PostDao;
import com.learning.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostEditController {

    private final PostDao postDao;
    private final PostEditValidator postEditValidator;

    public PostEditController(PostDao postDao, PostEditValidator postEditValidator1) {
        this.postDao = postDao;
        this.postEditValidator = postEditValidator1;
    }

    @RequestMapping(value = "/post/postEdit.html", method = RequestMethod.GET)
    public String onGet(
            Model model,
            @RequestParam(required = false) Integer Id
    ) {
        Post selectPost = postDao.getPostById(Id);
        if (selectPost==null){
            selectPost = new Post();
        }
        model.addAttribute("selectPost", selectPost);
        return "/post/postEdit";

    }
    @RequestMapping(value = "/post/postEdit.html", method = RequestMethod.POST, params = "!_cancel")
    public String onPost(
            Model model,
            @ModelAttribute("selectPost") Post selectPost,
            BindingResult result
    ) {
        postEditValidator.validate(selectPost, result);
        if (!result.hasErrors()) {
            postDao.SaveOrUpdate(selectPost);
        } else {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("selectPost", selectPost);
            return "/post/postEdit";
        }
        return "redirect:/post/postEdit.html?Id=" + selectPost.getId();
    }

    @RequestMapping(value = "/post/postEdit.html", method = RequestMethod.POST, params = "_cancel")
    public String onCancel() {
        return "redirect:/blog.html";
    }
}
