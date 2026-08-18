package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EligibilityVerificationResponse {
    private Long id;
    private Long registrationId;
    private Long testId;
    private String status;
    private String verifiedBy;
    private LocalDateTime verifiedAt;
    private String remarks;
    private boolean sscGpaVerified;
    private boolean hscGpaVerified;
    private boolean documentsVerified;
    private LocalDateTime createdAt;
}