package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CreditRuleRequest {
    private Long programId;
    private Integer minCreditsPerSemester;
    private Integer maxCreditsPerSemester;
    private Integer totalRequiredCredits;
    private Integer maxTransferCredits;
    private Integer maxElectiveCredits;
    private String description;
}
