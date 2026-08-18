package com.brilliantsofts.EliteUniversity.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class CourseRequest {
    @JsonAlias({"name", "title"})
    private String courseName;

    @JsonAlias({"code", "course_code"})
    private String courseCode;

    @JsonAlias({"durationYears", "duration_years", "credits"})
    private Integer credit;

    private String description;
    private Long departmentId;
    private Long programId;

    public String getCourseName() {
        return (courseName != null && !courseName.isBlank()) ? courseName : null;
    }

    public String getCourseCode() {
        return (courseCode != null && !courseCode.isBlank()) ? courseCode : null;
    }
}
