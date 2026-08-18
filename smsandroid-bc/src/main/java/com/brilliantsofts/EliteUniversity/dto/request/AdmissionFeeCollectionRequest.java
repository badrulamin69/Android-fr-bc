package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionFeeCollectionRequest {
    private String uniqueCode;
    private Long candidateId;
    private Double amount;
    private String paymentMethod;
    private String transactionId;
    private String status;
    private Long receivedBy;
    private String remarks;
}
