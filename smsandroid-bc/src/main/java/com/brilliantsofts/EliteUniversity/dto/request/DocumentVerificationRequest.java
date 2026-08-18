package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class DocumentVerificationRequest {
    private Long admissionCandidateId;
    private String documentType;
    private String documentNumber;
    private boolean isVerified;
    private Long verifiedBy;
    private String remarks;
}