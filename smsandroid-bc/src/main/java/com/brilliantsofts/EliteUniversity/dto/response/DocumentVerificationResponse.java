package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DocumentVerificationResponse {
    private Long id;
    private String uniqueCode;
    private Long admissionCandidateId;
    private String documentType;
    private String documentNumber;
    private boolean isVerified;
    private Long verifiedBy;
    private LocalDateTime verificationDate;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}