package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AccountRequest {
    private String uniqueCode;
    private String accountName;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String description;
    private Boolean isActive;
}
