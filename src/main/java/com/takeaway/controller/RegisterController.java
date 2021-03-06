package com.takeaway.controller;

import com.takeaway.model.response.CommonResponse;
import com.takeaway.repository.user.UserRepo;
import com.takeaway.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private
    RegisterService registerService;

    private
    UserRepo userRepository;

    @Autowired
    public RegisterController(
            RegisterService registerService,
            UserRepo userRepository
    ) {
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    CommonResponse register(String userName, String password, HttpSession session) {

        if (registerService.register(userName, password, session))
            return new CommonResponse("0", "注册成功");
        else
            return new CommonResponse("1", "网络连接失败，请稍后重试");

    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public
    CommonResponse verify(String userName) {

        if (userRepository.countByUserName(userName) > 0)
            return new CommonResponse("1", "用户名已存在");

        return new CommonResponse("0", "用户名可用");
    }

}
