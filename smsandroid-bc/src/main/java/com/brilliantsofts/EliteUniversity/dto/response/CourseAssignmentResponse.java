package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseAssignmentResponse {
    private Long id;
    private String uniqueCode;
    private Long courseId;
    private Long subjectId;
    private Long administrationId;
    private Integer semester;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}