package com.takeaway.model.order;

import com.alibaba.fastjson.JSON;
import com.takeaway.model.course.SelectedCourse;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "orderno")
    private String orderNo;

    @Column(name = "createtime")
    private Date createTime;

    private Integer state;          // -1 待配送, 0 配送成功, 1 配送失败

    @Column(name = "coursestring")
    private String coursesString;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "totalprice")
    private Long totalPrice;

    private String address;

    private transient List<SelectedCourse> courses;

    private transient String timeStamp;

    public void fmtTime() {
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }

    public void coursesStrToCourses() {
        this.courses = JSON.parseArray(this.coursesString, SelectedCourse.class);
    }

    public void coursesToCoursesStr(List<SelectedCourse> courses) {
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

    public String getCoursesString() {
        return coursesString;
    }

    public void setCoursesString(String coursesString) {
        this.coursesString = coursesString;
    }

    public List<SelectedCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<SelectedCourse> courses) {
        this.courses = courses;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
