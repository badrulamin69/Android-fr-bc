package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CourseRegistrationRequest {
    private Long studentId;
    private Long courseId;
    private Long semesterId;
    private Long batchId;
    private String status;
    private Integer creditHours;
    private String remarks;
}
