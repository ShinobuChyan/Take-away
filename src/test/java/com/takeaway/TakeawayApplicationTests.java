package com.takeaway;

import com.takeaway.model.course.Course;
import com.takeaway.model.course.SelectedCourse;
import com.takeaway.model.order.Order;
import com.takeaway.model.page.PageResponse;
import com.takeaway.model.user.Address;
import com.takeaway.model.user.User;
import com.takeaway.repository.course.CourseRepo;
import com.takeaway.repository.order.OrderRepo;
import com.takeaway.repository.user.UserRepo;
import com.takeaway.service.page.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TakeawayApplicationTests {

    @Autowired
    private
    CourseRepo courseRepository;

    @Autowired
    PageService pageService;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;


	@Test
	public void contextLoads() {

    }

}
