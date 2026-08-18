package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmitCardRequest {
    private Long registrationId;
    private Long testId;
    private String admitCardNumber;
    private String rollNumber;
    private String seatNumber;
    private String centerName;
    private String buildingName;
    private String roomName;
    private String qrCode;
    private LocalDateTime issuedAt;
    private String status;
}
