package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.PaymentMethod;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private Long id;
    private String paymentNumber;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long applicantId;
    private String applicantName;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private String gatewayResponse;
    private LocalDateTime paymentDate;
    private String createdBy;
    private String notes;
    private String receiptUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
