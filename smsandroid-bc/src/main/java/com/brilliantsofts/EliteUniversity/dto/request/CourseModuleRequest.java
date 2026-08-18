package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CourseModuleRequest {
    private String moduleTitle;
    private Integer moduleOrder;
    private String description;
    private Long courseId;
}
