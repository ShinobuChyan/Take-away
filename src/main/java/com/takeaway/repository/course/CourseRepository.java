package com.takeaway.repository.course;

import com.takeaway.model.course.Course;
import com.takeaway.model.menu.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor {

    Page<Course> findAll(Pageable pageable);

    @Query(value = "select u.id, u.type as type, count(u) as count from Course u group by type")
    @Modifying
    List<Object[]> countType();

}
