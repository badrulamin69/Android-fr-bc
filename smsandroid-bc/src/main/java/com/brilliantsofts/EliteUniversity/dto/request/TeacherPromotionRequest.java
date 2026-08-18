package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TeacherPromotionRequest {
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
}
