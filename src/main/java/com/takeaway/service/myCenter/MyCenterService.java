package com.takeaway.service.myCenter;

import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.model.user.User;

public interface MyCenterService {

    CommonResponse changePwd(User user, String oldPwd, String newPwd);

    PageResponse getOrderList(User user, Integer page);

}
