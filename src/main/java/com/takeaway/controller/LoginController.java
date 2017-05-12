package com.takeaway.controller;

import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.User;
import com.takeaway.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final
    UserRepo userRepository;

    @Autowired
    public LoginController(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommonResponse login(String userName, String password, HttpSession session) throws Exception {

        User user = userRepository.findByUserNameAndPassword(userName, password);

        // 若未找到对应用户则返回-1
        if (user == null)
            return new CommonResponse("-1", "登陆失败，用户名或密码错误");

        // 保存用户登陆信息
        session.setAttribute("userInfo", user);

        return new CommonResponse("0", "登陆成功");
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public CommonResponse logOut(HttpSession session) {
        session.invalidate();
        return new CommonResponse("0", "注销成功");
    }

    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public User getLoginInfo(HttpSession session) {
        return (User) session.getAttribute("userInfo");
    }

}
