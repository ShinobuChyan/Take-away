package com.takeaway.service.myCenter;

import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;
import com.takeaway.repository.user.AddressRepo;
import com.takeaway.repository.user.UserRepo;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCenterServiceImpl implements MyCenterService {

    private final UserRepo userRepo;

    private final PageService pageService;

    private final AddressRepo addressRepo;

    @Autowired
    public MyCenterServiceImpl(UserRepo userRepo, PageService pageService, AddressRepo addressRepo) {
        this.userRepo = userRepo;
        this.pageService = pageService;
        this.addressRepo = addressRepo;
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
        return pageService.orderList(page, user.getUserId());
    }

    @Override
    public CommonResponse saveAddressChanges(Address address) {

        addressRepo.save(address);

        return new CommonResponse("0", "修改地址成功");
    }


}