package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class EligibilityVerificationRequest {
    private Long registrationId;
    private Long testId;
    private String status;
    private String verifiedBy;
    private String remarks;
    private boolean sscGpaVerified;
    private boolean hscGpaVerified;
    private boolean documentsVerified;
}