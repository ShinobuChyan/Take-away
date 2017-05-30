package com.takeaway.controller;

import com.takeaway.model.user.AddressModel;
import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;
import com.takeaway.repository.user.AddressRepo;
import com.takeaway.repository.user.UserRepo;
import com.takeaway.service.myCenter.MyCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/userCenter")
public class UserCenterController {

    private final
    MyCenterService myCenterService;

    private final
    UserRepo userRepo;

    private final
    AddressRepo addressRepo;

    @Autowired
    public UserCenterController(MyCenterService myCenterService, UserRepo userRepo, AddressRepo addressRepo) {
        this.myCenterService = myCenterService;
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toMyCenter(HttpSession session) {
        if (session.getAttribute("userInfo") == null)
            return "redirect:/index";

        User user = (User) session.getAttribute("userInfo");
        if (user != null && user.getType() == 1)
            return "redirect:/manager";

        return "userCenter";
    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.GET)
    public String toChangeInfo() {
        return "myCenterChangeInfo";
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse changePwd(String oldPwd, String newPwd, HttpSession session) {
        return myCenterService.changePwd((User) session.getAttribute("userInfo"), oldPwd, newPwd);
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

    @RequestMapping(value = "/cancle")
    public
    @ResponseBody
    CommonResponse cancleOrder(Long id) {
        return myCenterService.cancleOrder(id);
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String toAddress() {
        return "myCenterAddress";
    }

    @RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Address> getAddressList(HttpSession session) {
        User user = (User) session.getAttribute("userInfo");
        return userRepo.findById(user.getId()).getAddressList();
    }

    @RequestMapping(value = "/changeAddress", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse saveAddressChanges(AddressModel addressModel) {

        Address address = new Address();
        address.setId(addressModel.getId());
        address.setName(addressModel.getName());
        address.setAddress(addressModel.getAddress());
        address.setPhone(addressModel.getPhone());

        return myCenterService.saveAddressChanges(address);
    }

    @RequestMapping(value = "/deleteAddress")
    public
    @ResponseBody
    CommonResponse deleteAddress(Long id) {

        addressRepo.delete(id);

        return new CommonResponse("0", "地址删除成功");
    }

}
