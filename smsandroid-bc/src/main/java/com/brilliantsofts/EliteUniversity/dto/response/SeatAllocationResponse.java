package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeatAllocationResponse {
    private Long id;
    private Long testId;
    private Long registrationId;
    private Long centerId;
    private String centerName;
    private String buildingName;
    private String roomName;
    private String seatNumber;
    private String rollNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
