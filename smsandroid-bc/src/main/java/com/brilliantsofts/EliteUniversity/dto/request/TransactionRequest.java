package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    private String uniqueCode;
    private Long accountId;
    private String transactionType;
    private Double amount;
    private String description;
    private String referenceType;
    private Long referenceId;
    private LocalDateTime transactionDate;
}
