package com.takeaway.service.page.impl;

import com.takeaway.model.order.Order;
import com.takeaway.model.course.Course;
import com.takeaway.model.page.PageResponse;
import com.takeaway.repository.course.CourseRepository;
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

    private CourseRepository courseRepository;

    private OrderRepo orderRepo;

    @Autowired
    public PageServiceImpl(
            CourseRepository courseRepository,
            OrderRepo orderRepo) {
        this.courseRepository = courseRepository;
        this.orderRepo = orderRepo;
    }

    @Override
    public PageResponse courseSearch(Integer page, Integer type) {

        Specification<Course> condition = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (type != null)
                predicates.add(cb.equal(root.get("type"), type));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        Page<Course> pages = courseRepository.findAll(condition, new PageRequest(page - 1, 20));

        return PageUtil.getPageResponse(pages);
    }

    @Override
    public PageResponse orderList(Integer page, Long userId) {

        Page<Order> pages = orderRepo.findByUserId(new PageRequest(page - 1, 20), userId);

        return PageUtil.getPageResponse(pages);
    }

}
