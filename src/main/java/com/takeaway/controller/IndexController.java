package com.takeaway.controller;

import com.takeaway.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController {

    @RequestMapping
    public String entrance(Model model, boolean needLogin) {

        if (needLogin)
            model.addAttribute("needLogin", true);

        return "index";
    }

}
