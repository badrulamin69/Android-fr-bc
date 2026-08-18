package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdvisorApprovalResponse {
    private Long id;
    private Long registrationId;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long courseId;
    private String courseName;
    private Long semesterId;
    private String semesterName;
    private Long advisorId;
    private String advisorName;
    private String action;
    private String comments;
    private LocalDateTime createdAt;
}
