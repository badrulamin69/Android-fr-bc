package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private Long id;
    private String uniqueCode;
    private String accountName;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
