package com.takeaway.controller;

import com.takeaway.model.course.Course;
import com.takeaway.model.order.Order;
import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.service.manager.ManagerService;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    private final CourseRepo courseRepo;

    private final PageService pageService;

    private final ManagerService managerService;

    private final ServletContext servletContext;

    @Autowired
    public ManagerController(CourseRepo courseRepo, PageService pageService, ManagerService managerService, ServletContext servletContext) {
        this.courseRepo = courseRepo;
        this.pageService = pageService;
        this.managerService = managerService;
        this.servletContext = servletContext;
    }

    @RequestMapping
    public String toManagerMain() {
        return "manager";
    }


    @RequestMapping(value = "/getOrderList")
    public
    @ResponseBody
    PageResponse getOrderList(Integer page, String orderNo) {
        return pageService.orderList(page, orderNo);
    }

    @RequestMapping(value = "/changeState")
    public
    @ResponseBody
    CommonResponse changeState(Long id) {

        try {
            managerService.changeState(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse("1", "网络异常，请稍后重试");
        }

        return new CommonResponse("0", "订单配送成功");

    }

    @RequestMapping(value = "/addCourse")
    public
    @ResponseBody
    CommonResponse addCourse(Course course, MultipartFile[] img) throws IOException {

//        course.setPrice(course.getPrice() * 100);

//        course.setImg(saveImg(img));

        courseRepo.save(course);
        return new CommonResponse("0", "菜品添加成功");
    }

    @RequestMapping(value = "/modifyCourse")
    public
    @ResponseBody
    CommonResponse modifyCourse(Course course, MultipartFile[] img) throws IOException {
//        course.setImg(saveImg(img));
        Course oldCourse;
        if (course.getId() != null) {
            oldCourse = courseRepo.findOne(course.getId());
            course.setVolume(oldCourse.getVolume());
        }

        if ("".equals(course.getImg()))
            course.setImg(null);
        courseRepo.save(course);
        return new CommonResponse("0", "菜品信息修改成功");
    }

    @RequestMapping("/deleteCourse")
    public
    @ResponseBody
    CommonResponse deleteCourse(Long id) {
        courseRepo.delete(id);
        return new CommonResponse("0", "菜品删除成功");
    }

}
