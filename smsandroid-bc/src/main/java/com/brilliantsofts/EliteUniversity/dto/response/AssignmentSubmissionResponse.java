package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentSubmissionResponse {
    private Long id;
    private String uniqueCode;
    private Long assignmentId;
    private String assignmentTitle;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private LocalDateTime submissionDate;
    private String fileUrl;
    private String notes;
    private Integer marksObtained;
    private String feedback;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
