package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FineRequest {
    private Long studentId;
    private Long invoiceId;
    private Long feeTypeId;
    private Double amount;
    private String reason;
    private String issuedBy;
    private String status;
    private LocalDate issuedDate;
}
