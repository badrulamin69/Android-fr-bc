package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class DepartmentAllocationRequest {
    private Long registrationId;
    private Long allocatedProgramId;
    private Long allocatedDepartmentId;
    private Long allocatedBatchId;
    private Long allocatedSectionId;
    private Long semesterId;
    private Long allocatedById;
    private Integer meritRank;
    private Double totalScore;
    private String status;
    private String remarks;
}