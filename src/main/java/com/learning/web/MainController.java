package com.learning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {

    @GetMapping({"", "/", "/index"})
    public String Home(Model model){
        return "index";
    }
}
