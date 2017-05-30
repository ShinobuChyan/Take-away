package com.takeaway.service.myCenter.impl;

import com.takeaway.model.order.Order;
import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.repository.user.AddressRepo;
import com.takeaway.repository.user.UserRepo;
import com.takeaway.service.myCenter.MyCenterService;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class MyCenterServiceImpl implements MyCenterService {

    private final UserRepo userRepo;

    private final PageService pageService;

    private final AddressRepo addressRepo;

    private final OrderRepo orderRepo;

    private final HttpSession session;

    @Autowired
    public MyCenterServiceImpl(UserRepo userRepo, PageService pageService, AddressRepo addressRepo, OrderRepo orderRepo, HttpSession session) {
        this.userRepo = userRepo;
        this.pageService = pageService;
        this.addressRepo = addressRepo;
        this.orderRepo = orderRepo;
        this.session = session;
    }

    @Override
    public CommonResponse changePwd(User user, String oldPwd, String newPwd) {

        if (user == null || oldPwd == null || newPwd == null)
            return new CommonResponse("1", "密码为空");

        if (!oldPwd.equals(user.getPassword()))
            return new CommonResponse("1", "原密码错误");

        if (oldPwd.equals(newPwd))
            return new CommonResponse("1", "新密码与原密码相同");

        user.setPassword(newPwd);
        userRepo.save(user);
        return new CommonResponse("0", "更改密码成功");
    }

    @Override
    public PageResponse getOrderList(User user, Integer page) {
        return pageService.orderList(page, user.getId());
    }

    @Override
    public CommonResponse cancleOrder(Long id) {

        Order order = orderRepo.findById(id);

        if (order.getState() == 0)
            return new CommonResponse("1", "订单已被配送，无法取消");

        if (new Date().getTime() - order.getCreateTime().getTime() > 1800000)
            return new CommonResponse("1", "订单建立超过三十分钟，无法取消");

        orderRepo.delete(id);

        return new CommonResponse("0", "订单取消成功");
    }

    @Override
    public CommonResponse saveAddressChanges(Address address) {

        User user = (User) session.getAttribute("userInfo");
        address.setUserId(user.getId());

        addressRepo.save(address);

        return new CommonResponse("0", "修改地址成功");
    }


}
