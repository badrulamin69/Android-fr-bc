package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionEnrollmentResponse {
    private Long id;
    private String uniqueCode;
    private String enrollmentNumber;
    private Long applicationId;
    private Long studentId;
    private Long offerLetterId;
    private Long programId;
    private Long semesterId;
    private Long batchId;
    private Long sectionId;
    private String status;
    private LocalDateTime enrolledAt;
    private String remarks;
    private Boolean isDocumentVerified;
    private Boolean isFeePaid;
    private Double totalFeePaid;
    private Long enrolledById;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
