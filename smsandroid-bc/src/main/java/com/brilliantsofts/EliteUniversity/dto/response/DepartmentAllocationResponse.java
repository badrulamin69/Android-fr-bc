package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DepartmentAllocationResponse {
    private Long id;
    private String allocationNumber;
    private Integer meritRank;
    private Double totalScore;
    private String status;
    private LocalDateTime allocatedAt;
    private LocalDateTime confirmedAt;
    private String remarks;
    private Long registrationId;
    private Long allocatedProgramId;
    private Long allocatedDepartmentId;
    private Long allocatedBatchId;
    private Long allocatedSectionId;
    private Long semesterId;
    private Long allocatedById;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}