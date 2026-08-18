package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GradeRuleResponse {
    private Long id;
    private String uniqueCode;
    private Long courseId;
    private String courseName;
    private String grade;
    private BigDecimal minPercentage;
    private BigDecimal maxPercentage;
    private BigDecimal gradePoint;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
