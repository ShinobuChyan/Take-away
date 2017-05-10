package com.takeaway.controller;

import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.User;
import com.takeaway.service.myCenter.MyCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/myCenter")
public class MyCenterController {

    private final
    MyCenterService myCenterService;

    @Autowired
    public MyCenterController(MyCenterService myCenterService) {
        this.myCenterService = myCenterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toMyCenter(HttpSession session) {
        if (session.getAttribute("userInfo") == null)
            return "redirect:/index";

        return "myCenter";
    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.GET)
    public String toChangeInfo() {
        return "myCenterChangeInfo";
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public CommonResponse changePwd(String oldPwd, String newPwd, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        return myCenterService.changePwd(user, oldPwd, newPwd);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String toOrderList(HttpSession session) {
        // TODO Filter
        return "myCenterOrderList";
    }

    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    public
    @ResponseBody
    PageResponse getOrderList(Integer page, HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        return myCenterService.getOrderList(user, page);
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String toAddress() {
        return "myCenterAddress";
    }

}
