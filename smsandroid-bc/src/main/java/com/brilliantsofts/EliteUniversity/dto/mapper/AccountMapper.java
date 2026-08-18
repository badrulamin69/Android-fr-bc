package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AccountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AccountResponse;
import com.brilliantsofts.EliteUniversity.entity.Account;

public class AccountMapper {
    public static Account toEntity(AccountRequest request) {
        Account entity = new Account();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setAccountName(request.getAccountName());
        entity.setAccountNumber(request.getAccountNumber());
        entity.setAccountType(request.getAccountType());
        entity.setBalance(request.getBalance());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static AccountResponse toResponse(Account entity) {
        AccountResponse response = new AccountResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setAccountName(entity.getAccountName());
        response.setAccountNumber(entity.getAccountNumber());
        response.setAccountType(entity.getAccountType());
        response.setBalance(entity.getBalance());
        response.setDescription(entity.getDescription());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
