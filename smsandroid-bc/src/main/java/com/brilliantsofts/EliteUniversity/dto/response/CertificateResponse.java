package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CertificateResponse {
    private Long id;
    private String uniqueCode;
    private String certificateNumber;
    private Long studentId;
    private String certificateType;
    private LocalDateTime issuedAt;
    private LocalDate validUntil;
    private String status;
    private String purpose;
    private Long issuedById;
    private Boolean isDownloaded;
}
