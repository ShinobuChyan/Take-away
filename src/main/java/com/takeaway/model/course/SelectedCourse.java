package com.takeaway.model.course;

import java.util.List;
import java.util.Map;

public class SelectedCourse {

    private Course course;

    private Integer count;

    public SelectedCourse() {

    }

    public SelectedCourse(Course course, Integer count) {
        this.count = count;
        this.course = course;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
