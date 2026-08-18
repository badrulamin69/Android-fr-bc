package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class FeeStructureRequest {
    private Long feeTypeId;
    private Long programId;
    private Long semesterId;
    private Long batchId;
    private Double amount;
    private Integer dueDays;
    private String academicYear;
    private String description;
    private Boolean isActive;
}
