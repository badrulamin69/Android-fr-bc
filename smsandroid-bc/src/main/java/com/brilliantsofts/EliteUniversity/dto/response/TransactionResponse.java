package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private String uniqueCode;
    private Long accountId;
    private String transactionType;
    private Double amount;
    private String description;
    private String referenceType;
    private Long referenceId;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
