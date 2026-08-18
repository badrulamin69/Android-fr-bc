package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeeStructureResponse {
    private Long id;
    private Long feeTypeId;
    private Long programId;
    private Long semesterId;
    private Long batchId;
    private Double amount;
    private Integer dueDays;
    private String academicYear;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
