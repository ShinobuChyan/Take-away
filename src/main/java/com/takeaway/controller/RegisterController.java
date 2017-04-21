package com.takeaway.controller;

import com.takeaway.repository.user.UserRepository;
import com.takeaway.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private
    RegisterService registerService;

    private
    UserRepository userRepository;

    @Autowired
    public RegisterController(
            RegisterService registerService,
            UserRepository userRepository
    ) {
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String register(String userName, String password) {
        if (registerService.register(userName, password))
            return "0";
        else
            return "1";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public
    @ResponseBody
    String verify(String username) {

        if (userRepository.countByUserName(username) > 0)
            return "-1";

        return "0";
    }

}
