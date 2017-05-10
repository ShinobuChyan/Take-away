package com.takeaway.controller;

import com.takeaway.model.menu.Menu;
import com.takeaway.model.page.PageResponse;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private PageService pageService;

    private CourseRepo courseRepository;

    @Autowired
    public MainController(
            PageService pageService,
            CourseRepo courseRepository
    ) {
        this.pageService = pageService;
        this.courseRepository = courseRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userMain(String searchContent) {

        if (searchContent == null || "".equals(searchContent))
            return "main";

        // TODO 中文搜索
        return "main";
    }

    @RequestMapping(value = "/courseSearch", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResponse mainCourseSearch(Integer page, Integer type) {
        return pageService.courseSearch(page, type);
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Menu> getMenu() {
        return Menu.getMenu(courseRepository.countType());
    }

}
