package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificateRequest {
    private String certificateNumber;
    private Long studentId;
    private String certificateType;
    private LocalDate validUntil;
    private String status;
    private String purpose;
    private Long issuedById;
}
