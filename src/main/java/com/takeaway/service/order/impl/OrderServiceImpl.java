package com.takeaway.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.takeaway.model.course.Course;
import com.takeaway.model.course.SelectedCourse;
import com.takeaway.model.order.NewOrder;
import com.takeaway.model.order.Order;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.repository.user.AddressRepo;
import com.takeaway.service.order.OrderService;
import com.takeaway.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final
    OrderRepo orderRepo;

    private final
    CourseRepo courseRepo;

    private final
    HttpSession session;

    private final
    AddressRepo addressRepo;

    @Autowired
    public OrderServiceImpl(
            OrderRepo orderRepo,
            CourseRepo courseRepo,
            HttpSession session, AddressRepo addressRepo) {
        this.orderRepo = orderRepo;
        this.courseRepo = courseRepo;
        this.session = session;
        this.addressRepo = addressRepo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitOrder(NewOrder newOrder) throws Exception {

        List<SelectedCourse> courses = new ArrayList<>();
        final Long[] totalPrice = {0L};

        newOrder.getNewOrder().forEach(map -> {

            Course course = courseRepo.findById(Long.valueOf(map.get("id")));
            courses.add(new SelectedCourse(course, map.get("count")));

            course.setVolume(course.getVolume() + map.get("count"));
            courseRepo.save(course);

            totalPrice[0] += course.getPrice() * map.get("count");
        });

        String cousesStr = JSON.toJSONString(courses);
        Date date = new Date();
        User user = (User) session.getAttribute("userInfo");
        Address address = addressRepo.findOne(Long.valueOf(newOrder.getAddressId()));

        Order order = new Order();
        order.setOrderNo(CommonUtil.newOrderNo("ORDER", date));
        order.setCreateTime(date);
        order.setState(-1);
        order.setCoursesString(cousesStr);
        order.setUserId(user.getId());
        order.setTotalPrice(totalPrice[0]);
        order.setAddress("联系人：" +
                address.getName() +
                "，联系电话：" +
                address.getPhone() +
                "，地址：" +
                address.getAddress());
        orderRepo.save(order);

    }

}
