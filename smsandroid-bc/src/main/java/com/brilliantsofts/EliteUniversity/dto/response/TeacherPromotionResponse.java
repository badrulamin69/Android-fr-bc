package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TeacherPromotionResponse {
    private Long id;
    private String uniqueCode;
    private Long teacherId;
    private String previousDesignation;
    private String newDesignation;
    private String previousDepartment;
    private String newDepartment;
    private String previousSalaryGrade;
    private String newSalaryGrade;
    private BigDecimal previousSalary;
    private BigDecimal newSalary;
    private LocalDate promotionDate;
    private String reason;
    private Long approvedBy;
    private String approvedByName;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
