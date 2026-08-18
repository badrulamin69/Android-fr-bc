package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class InvoiceItemRequest {
    private Long feeTypeId;
    private String description;
    private Double amount;
    private Double discountAmount;
    private Double netAmount;
}
