package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AcademicPolicyResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String description;
    private String policyType;
    private String policyValue;
    private Long programId;
    private boolean isActive;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
