package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentConfigResponse {
    private Long id;
    private Long semesterId;
    private String semesterName;
    private Long academicSessionId;
    private String academicSessionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime lateEnrollmentDate;
    private Integer minCredits;
    private Integer maxCredits;
    private String enrollmentStatus;
    private boolean active;
    private boolean closed;
    private boolean requiresAdvisorApproval;
    private boolean requiresPayment;
    private boolean allowLateEnrollment;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
