package com.learning.web.user;

import com.learning.dao.UserDao;
import com.learning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ulyanov on 30.09.16.
 */
@Controller
public class UserEditController {
    @Autowired private UserDao userDao;
    @Autowired private UserEditValidator userEditValidator;

    @RequestMapping(value = "/user/userEdit.html", method = RequestMethod.GET)
    public String onGet(
            Model model,
            @RequestParam(required = false) Integer userId
    ) {
        User command = userDao.getUserByUserId(userId);
        if (command==null){
            command = new User();
            command.setUserRole("ROLE_USER");
        }
        model.addAttribute("command", command);
        return "user/userEdit";
    }

    @RequestMapping(value = "/user/userEdit.html", method = RequestMethod.POST, params = "!_cancel")
    public String onPost(
            Model model,
            @ModelAttribute("command") User command,
            BindingResult result
    ) {
        userEditValidator.validate(command, result);
        if (!result.hasErrors()) {
            userDao.saveOrUpdate(command);
        } else {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("command", command);
            return "user/userEdit";
        }
        return "redirect:/user/userEdit.html?userId=" + command.getUserId();
    }

    @RequestMapping(value = "/user/userEdit.html", method = RequestMethod.POST, params = "_cancel")
    public String onCancel() {
        return "redirect:/user/users.html";
    }
}
