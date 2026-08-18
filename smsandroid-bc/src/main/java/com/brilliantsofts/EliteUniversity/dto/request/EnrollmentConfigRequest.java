package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentConfigRequest {
    private Long semesterId;
    private Long academicSessionId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime lateEnrollmentDate;
    private Integer minCredits;
    private Integer maxCredits;
    private String enrollmentStatus;
    private boolean active;
    private boolean requiresAdvisorApproval;
    private boolean requiresPayment;
    private boolean allowLateEnrollment;
    private String remarks;
}
