package com.takeaway;

import com.takeaway.model.course.Course;
import com.takeaway.model.menu.Menu;
import com.takeaway.repository.course.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TakeawayApplicationTests {

    @Autowired
    CourseRepository courseRepository;

	@Test
	public void contextLoads() {

        Page<Course> pages = courseRepository.findAll(new PageRequest(0, 20));

    }

}
