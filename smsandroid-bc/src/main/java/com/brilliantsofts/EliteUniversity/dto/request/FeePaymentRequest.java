package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class FeePaymentRequest {
    private Double amount;
    private String paymentMethod;
    private String transactionId;
}
