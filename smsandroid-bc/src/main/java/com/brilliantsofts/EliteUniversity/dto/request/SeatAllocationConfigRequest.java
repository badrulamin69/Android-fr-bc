package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeatAllocationConfigRequest {
    private Long sessionId;
    private String academicYear;
    private Integer allocationRound;
    private Boolean autoAllocation;
    private Boolean manualAllocation;
    private LocalDateTime allocationStartDate;
    private LocalDateTime allocationEndDate;
    private Integer acceptDeadlineHours;
    private Boolean lockAfterPublish;
    private Boolean enableQuota;
    private Boolean enableReservedSeats;
    private String status;
    private String remarks;
}
