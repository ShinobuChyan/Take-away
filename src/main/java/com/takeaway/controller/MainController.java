package com.takeaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @RequestMapping
    public String userMain(String searchContent) {
        return "userMain";
    }

}
