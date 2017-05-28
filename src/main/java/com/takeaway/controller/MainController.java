package com.takeaway.controller;

import com.takeaway.model.menu.Menu;
import com.takeaway.model.order.NewOrder;
import com.takeaway.model.page.PageResponse;
import com.takeaway.model.response.CommonResponse;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.service.order.OrderService;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private final PageService pageService;

    private final CourseRepo courseRepository;

    private final OrderService orderService;

    @Autowired
    public MainController(
            PageService pageService,
            CourseRepo courseRepository,
            OrderService orderService) {
        this.pageService = pageService;
        this.courseRepository = courseRepository;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userMain() {
        return "main";
    }

    @RequestMapping(value = "/courseSearch", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResponse mainCourseSearch(Integer page, Integer type, String courseName) {
        return pageService.courseSearch(page, type, courseName);
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Menu> getMenu() {
        return Menu.getMenu(courseRepository.countType());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse submit(NewOrder newOrder) {

        try {
            orderService.submitOrder(newOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse("1", "订单提交失败，请重新尝试");
        }

        return new CommonResponse("0", "订单提交成功");
    }

}
