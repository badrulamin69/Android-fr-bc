package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionOfferLetterRequest {
    private String uniqueCode;
    private String letterNumber;
    private Long applicationId;
    private Long meritListEntryId;
    private LocalDateTime issuedAt;
    private LocalDateTime validUntil;
    private String status;
    private String letterContent;
    private String conditions;
    private String remarks;
    private Long issuedById;
    private LocalDateTime acceptedAt;
    private LocalDateTime declinedAt;
    private String declineReason;
    private Boolean isDownloaded;
}
