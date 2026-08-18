package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProgramSeatAllocationResponse {
    private Long id;
    private String allocationNumber;
    private Long configId;
    private Integer allocationRound;
    private Integer choiceNumber;
    private Long allocatedFacultyId;
    private String shift;
    private Integer meritRank;
    private BigDecimal totalScore;
    private String status;
    private LocalDateTime allocatedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime declinedAt;
    private LocalDateTime deadline;
    private LocalDateTime confirmedAt;
    private boolean isWaiting;
    private Integer waitingRank;
    private String remarks;
    private Long registrationId;
    private Long allocatedProgramId;
    private Long allocatedDepartmentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
