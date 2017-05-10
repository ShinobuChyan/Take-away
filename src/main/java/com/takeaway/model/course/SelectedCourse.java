package com.takeaway.model.course;

import java.util.List;
import java.util.Map;

public class SelectedCourse {

    private Long courseId;

    private Integer count;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
