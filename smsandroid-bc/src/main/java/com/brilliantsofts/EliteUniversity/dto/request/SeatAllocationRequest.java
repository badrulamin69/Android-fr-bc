package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class SeatAllocationRequest {
    private Long testId;
    private Long registrationId;
    private Long centerId;
    private String centerName;
    private String buildingName;
    private String roomName;
    private String seatNumber;
    private String rollNumber;
    private String status;
}
