package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SemesterEnrollmentResponse {
    private Long id;
    private String enrollmentNumber;
    private Long studentId;
    private Long semesterId;
    private Long batchId;
    private Long programId;
    private Long facultyId;
    private Long departmentId;
    private Long advisorId;
    private LocalDateTime enrollmentDate;
    private String status;
    private Integer registeredCredits;
    private Integer minCredits;
    private Integer maxCredits;
    private String advisorStatus;
    private String advisorComments;
    private LocalDateTime advisorApprovedAt;
    private String paymentStatus;
    private BigDecimal paymentAmount;
    private String paymentReference;
    private LocalDateTime paymentDate;
    private boolean isFinalized;
    private LocalDateTime finalizedAt;
    private String remarks;
    private boolean isActive;
    private boolean isLateEnrollment;
    private String enrollmentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
