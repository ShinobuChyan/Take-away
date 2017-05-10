package com.takeaway.model.order;

import com.alibaba.fastjson.JSON;
import com.takeaway.model.course.SelectedCourse;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "orderno")
    private String orderNo;

    @Column(name = "createtime")
    private Date createTime;

    private Integer state;          // -1 待支付, 0 支付成功, 1 支付失败

    @Column(name = "coursestring")
    private String coursesString;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "paymethod")
    private Integer payMethod;

    public List<SelectedCourse> getCourses() {
        return JSON.parseArray(this.coursesString, SelectedCourse.class);
    }

    public void setCourses(List<SelectedCourse> courses) {
        this.coursesString = JSON.toJSONString(courses);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getCoursesString() {
        return coursesString;
    }

    public void setCoursesString(String coursesString) {
        this.coursesString = coursesString;
    }
}
