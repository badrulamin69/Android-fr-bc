package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionConfirmationResponse {
    private Long id;
    private String confirmationNumber;
    private Long allocationId;
    private Long registrationId;
    private String status;
    private Boolean documentsSubmitted;
    private Boolean documentsVerified;
    private Long documentsVerifiedBy;
    private LocalDateTime documentsVerifiedAt;
    private String documentRemarks;
    private Boolean feePaid;
    private Double feeAmount;
    private String feePaymentMethod;
    private String feeTransactionId;
    private LocalDateTime feePaidAt;
    private LocalDateTime confirmedAt;
    private Long confirmedBy;
    private String remarks;
    private Long sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Enriched Relations & UI Fields
    private PreAdmissionResponse registration;
    private DepartmentAllocationResponse allocation;
    private String registrationNumber;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String programName;
    private String departmentName;
    private String batchName;
}
