package com.takeaway.repository.course;

import com.takeaway.model.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAll(PageRequest pageRequest);

}
