package com.takeaway.controller;

import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.User;
import com.takeaway.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final
    UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommonResponse login(String userName, String password, HttpSession session) throws Exception {

        User user = userRepository.findByUserNameAndPassword(userName, password);

        // 若未找到对应用户则返回-1
        if (user == null)
            return new CommonResponse("-1", "登陆失败");

        // 保存用户登陆信息
        session.setAttribute("userInfo", user);

        return new CommonResponse("0", "登陆成功");
    }

    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public
    User getLoginInfo(HttpSession session) {
        return (User) session.getAttribute("userInfo");
    }

}
