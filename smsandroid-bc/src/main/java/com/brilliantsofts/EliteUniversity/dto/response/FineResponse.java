package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FineResponse {
    private Long id;
    private Long studentId;
    private Long invoiceId;
    private Long feeTypeId;
    private Double amount;
    private String reason;
    private String issuedBy;
    private String status;
    private LocalDate issuedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
