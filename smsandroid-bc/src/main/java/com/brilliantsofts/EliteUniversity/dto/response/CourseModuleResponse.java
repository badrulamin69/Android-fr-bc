package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class CourseModuleResponse {
    private Long id;
    private String moduleTitle;
    private Integer moduleOrder;
    private String description;
    private Long courseId;
    private String courseName;
}
