package com.takeaway.service.register.impl;

import com.takeaway.model.user.User;
import com.takeaway.repository.user.UserRepository;
import com.takeaway.service.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final
    UserRepository userRepository;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean register(String userName, String password, HttpSession session) {

        if (userRepository.countByUserName(userName) > 0)
            return false;

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setType(0);
        userRepository.save(user);
        session.setAttribute("userInfo", user);

        return true;
    }

}
