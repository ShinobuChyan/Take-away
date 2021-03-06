package com.takeaway.service.page.impl;

import com.takeaway.model.order.Order;
import com.takeaway.model.course.Course;
import com.takeaway.model.page.PageResponse;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.service.page.PageService;
import com.takeaway.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    private CourseRepo courseRepository;

    private OrderRepo orderRepo;

    @Autowired
    public PageServiceImpl(
            CourseRepo courseRepository,
            OrderRepo orderRepo) {
        this.courseRepository = courseRepository;
        this.orderRepo = orderRepo;
    }

    @Override
    public PageResponse courseSearch(Integer page, Integer type, String courseName) {

        Specification condition = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (type != null)
                predicates.add(cb.equal(root.get("type"), type));
            if (courseName != null && !"".equals(courseName))
                predicates.add(cb.equal(root.get("name"), courseName));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        Page pages = courseRepository.findAll(condition, new PageRequest(page - 1, 20));

        return PageUtil.getPageResponse(pages);
    }

    @Override
    public PageResponse orderList(Integer page, Long userId) {

        Page<Order> pages = orderRepo.findByUserId(new PageRequest(page - 1, 20), userId);
        PageResponse resp = PageUtil.getPageResponse(pages);
        List<Order> cont = resp.getContent();
        cont.forEach(order -> {
            order.coursesStrToCourses();
            order.fmtTime();
        });
        resp.setContent(cont);

        return resp;
    }

    @Override
    public PageResponse orderList(Integer page, String orderNo) {

        Specification<Order> condition = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (orderNo != null)
                predicates.add(cb.equal(root.get("orderNo"), orderNo));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        Page<Order> pages = orderRepo.findAll(condition, new PageRequest(page - 1, 10));
        PageResponse resp = PageUtil.getPageResponse(pages);
        List<Order> cont = resp.getContent();
        cont.forEach(order -> {
            order.coursesStrToCourses();
            order.fmtTime();
        });
        resp.setContent(cont);

        return resp;
    }

}
