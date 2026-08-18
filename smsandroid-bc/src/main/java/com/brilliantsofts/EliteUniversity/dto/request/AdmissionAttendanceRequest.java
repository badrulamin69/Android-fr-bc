package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionAttendanceRequest {
    private Long testId;
    private Long registrationId;
    private Long attemptId;
    private String status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Long markedById;
    private String remarks;
    private Boolean qrScanned;
}
