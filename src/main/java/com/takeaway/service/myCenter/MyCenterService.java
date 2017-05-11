package com.takeaway.service.myCenter;

import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;

import java.util.List;

public interface MyCenterService {

    CommonResponse changePwd(User user, String oldPwd, String newPwd);

    PageResponse getOrderList(User user, Integer page);

    CommonResponse saveAddressChanges(Address address);

}
