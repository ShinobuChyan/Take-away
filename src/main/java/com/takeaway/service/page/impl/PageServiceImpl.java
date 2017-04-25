package com.takeaway.service.page.impl;

import com.takeaway.model.course.Course;
import com.takeaway.model.page.PageResponse;
import com.takeaway.repository.course.CourseRepository;
import com.takeaway.service.page.PageService;
import com.takeaway.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    private CourseRepository courseRepository;

    @Autowired
    public PageServiceImpl(
            CourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
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

}
