package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProgramSeatAllocationRequest {
    private Long configId;
    private Integer allocationRound;
    private Integer choiceNumber;
    private Long allocatedFacultyId;
    private String shift;
    private Integer meritRank;
    private BigDecimal totalScore;
    private String status;
    private Long registrationId;
    private Long allocatedProgramId;
    private Long allocatedDepartmentId;
    private boolean isWaiting;
    private Integer waitingRank;
    private String remarks;
}
