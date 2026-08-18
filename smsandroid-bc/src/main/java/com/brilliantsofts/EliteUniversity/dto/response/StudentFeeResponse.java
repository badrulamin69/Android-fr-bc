package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentFeeResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long feeTypeId;
    private Double amount;
    private LocalDate dueDate;
    private Double paidAmount;
    private String status;
    private String academicYear;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
