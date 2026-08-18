package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Integer maxMarks;
    private Long courseId;
    private String courseName;
    private Long subjectId;
    private String subjectName;
    private Long sectionId;
    private String sectionName;
    private Long administrationId;
    private String administrationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
