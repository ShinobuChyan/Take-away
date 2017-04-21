package com.takeaway.controller;

import com.takeaway.model.user.User;
import com.takeaway.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    public
    @ResponseBody
    String login(String username, String password, HttpSession session) throws Exception {

        User user = userRepository.findByUserNameAndPassword(username, password);

        // 若未找到对应用户则返回-1
        if (user == null)
            return "-1";

        // 保存用户登陆信息
        session.setAttribute("userInfo", user);

        return user.getType().toString();
    }

    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    User getLoginInfo(HttpSession session) {
        return (User) session.getAttribute("userInfo");
    }

}
