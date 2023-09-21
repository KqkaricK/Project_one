package com.learning.web;

import com.learning.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/login.html")
    public String getLogin(
            @RequestParam(required = false) String error,
            Model model)
    {
        model.addAttribute("error",error);
        return "login";
    }

    @RequestMapping({"", "/", "/index.html"})
    public String getAdminForm(){
        return "index";
    }
}
