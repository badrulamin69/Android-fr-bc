package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreditRuleResponse {
    private Long id;
    private String uniqueCode;
    private Long programId;
    private Integer minCreditsPerSemester;
    private Integer maxCreditsPerSemester;
    private Integer totalRequiredCredits;
    private Integer maxTransferCredits;
    private Integer maxElectiveCredits;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
