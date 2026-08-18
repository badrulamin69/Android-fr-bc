package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class GradeRuleRequest {
    private Long courseId;
    private String grade;
    private BigDecimal minPercentage;
    private BigDecimal maxPercentage;
    private BigDecimal gradePoint;
    private String description;
}
