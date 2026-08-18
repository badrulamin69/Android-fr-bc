package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AcademicPolicyRequest {
    private String name;
    private String description;
    private String policyType;
    private String policyValue;
    private Long programId;
    private boolean isActive;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
}
