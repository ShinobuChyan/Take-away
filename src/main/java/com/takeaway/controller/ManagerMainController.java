package com.takeaway.controller;

import com.takeaway.model.course.Course;
import com.takeaway.model.order.Order;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.repository.order.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/manager")
public class ManagerMainController {

    private final CourseRepo courseRepo;

    private final OrderRepo orderRepo;

    @Autowired
    public ManagerMainController(CourseRepo courseRepo, OrderRepo orderRepo) {
        this.courseRepo = courseRepo;
        this.orderRepo = orderRepo;
    }

    public String toManagerMain() {
        return "managerMain";
    }

    public CommonResponse changeCourse(Course course) {
        courseRepo.save(course);
        return new CommonResponse("0", "修改成功");
    }

    public CommonResponse changeOrder(Order order) {
        orderRepo.save(order);
        return new CommonResponse("0", "修改成功");
    }

}
