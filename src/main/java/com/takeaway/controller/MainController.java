package com.takeaway.controller;

import com.takeaway.model.page.PageResponse;
import com.takeaway.service.page.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    private PageService pageService;

    @Autowired
    public MainController(
            PageService pageService
    ) {
        this.pageService = pageService;
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
    PageResponse mainCourseSearch(Integer page, Integer size, Integer type) {
        return pageService.courseSearch(page, size, type);
    }

}
