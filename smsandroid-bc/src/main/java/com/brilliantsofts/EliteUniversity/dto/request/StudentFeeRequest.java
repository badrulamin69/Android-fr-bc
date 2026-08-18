package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentFeeRequest {
    private String uniqueCode;
    private Long studentId;
    private Long feeTypeId;
    private Double amount;
    private LocalDate dueDate;
    private Double paidAmount;
    private String status;
    private String academicYear;
}
