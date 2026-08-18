package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class CourseResponse {
    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credit;
    private String description;
    private Long departmentId;
    private String departmentName;
    private Long programId;
    private String programName;

    public String getName() {
        return courseName;
    }

    public String getCode() {
        return courseCode;
    }

    public Integer getDurationYears() {
        return credit;
    }
}
