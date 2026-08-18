package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentSubmissionRequest {
    private Long assignmentId;
    private Long studentId;
    private LocalDateTime submissionDate;
    private String fileUrl;
    private String notes;
    private Integer marksObtained;
    private String feedback;
    private String status;
}
