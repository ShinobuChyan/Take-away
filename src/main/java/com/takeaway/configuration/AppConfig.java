package com.takeaway.configuration;

import com.takeaway.filter.AuthorityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class AppConfig {

//    @Bean
//    public FilterRegistrationBean authorityFilterBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(authorityFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("authorityFilter");
//        registration.setOrder(1);
//        System.out.println("authorityFilter set success!");
//        return registration;
//    }
//
//    @Bean(name = "authorityFilter")
//    public Filter authorityFilter() {
//        return new AuthorityFilter();
//    }

}
