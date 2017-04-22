package com.takeaway.service.register;

import javax.servlet.http.HttpSession;

public interface RegisterService {

    boolean register(String userName, String password, HttpSession session);

}
