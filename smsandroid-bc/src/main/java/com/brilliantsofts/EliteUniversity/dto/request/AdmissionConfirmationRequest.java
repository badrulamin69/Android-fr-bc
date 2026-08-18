package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionConfirmationRequest {
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
}
