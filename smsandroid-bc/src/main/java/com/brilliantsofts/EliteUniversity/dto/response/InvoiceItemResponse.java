package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceItemResponse {
    private Long id;
    private Long invoiceId;
    private Long feeTypeId;
    private String description;
    private Double amount;
    private Double discountAmount;
    private Double netAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
