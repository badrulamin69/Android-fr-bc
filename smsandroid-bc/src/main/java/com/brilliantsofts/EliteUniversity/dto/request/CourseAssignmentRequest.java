package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CourseAssignmentRequest {
    private Long courseId;
    private Long subjectId;
    private Long administrationId;
    private Integer semester;
}