package com.takeaway;

import com.takeaway.repository.course.CourseRepository;
import com.takeaway.service.page.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TakeawayApplicationTests {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PageService pageService;

	@Test
	public void contextLoads() {

    }

}
