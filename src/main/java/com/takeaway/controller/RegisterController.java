package com.takeaway.controller;

import com.takeaway.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RegisterController {

    private final
    RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping
    public String register(String userName, String password) {
        if (registerService.register(userName, password))
            return "0";
        else
            return "1";
    }

}
