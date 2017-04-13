package com.takeaway.controller;

import com.takeaway.model.user.User;
import com.takeaway.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final
    UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping
    public String login(String userName, String password) {

        User user = userRepository.findByUserNameAndPassword(userName, password);

        if (user.getType() == 0)
            return "userMain";
        else
            return "adminMain";
    }

}
