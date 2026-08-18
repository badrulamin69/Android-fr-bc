package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TransactionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransactionResponse;
import com.brilliantsofts.EliteUniversity.entity.Transaction;

public class TransactionMapper {
    public static Transaction toEntity(TransactionRequest request) {
        Transaction entity = new Transaction();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setAccountId(request.getAccountId());
        entity.setTransactionType(request.getTransactionType());
        entity.setAmount(request.getAmount());
        entity.setDescription(request.getDescription());
        entity.setReferenceType(request.getReferenceType());
        entity.setReferenceId(request.getReferenceId());
        entity.setTransactionDate(request.getTransactionDate());
        return entity;
    }

    public static TransactionResponse toResponse(Transaction entity) {
        TransactionResponse response = new TransactionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setAccountId(entity.getAccountId());
        response.setTransactionType(entity.getTransactionType());
        response.setAmount(entity.getAmount());
        response.setDescription(entity.getDescription());
        response.setReferenceType(entity.getReferenceType());
        response.setReferenceId(entity.getReferenceId());
        response.setTransactionDate(entity.getTransactionDate());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
