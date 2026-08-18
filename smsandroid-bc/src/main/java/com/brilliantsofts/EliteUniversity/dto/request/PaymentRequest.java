package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.PaymentMethod;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentRequest {
    private Long invoiceId;
    private Long studentId;
    private Long applicantId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private String notes;
    private String createdBy;
}
