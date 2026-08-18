package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.FineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FineResponse;
import com.brilliantsofts.EliteUniversity.entity.Fine;

public class FineMapper {
    public static Fine toEntity(FineRequest request) {
        Fine entity = new Fine();
        entity.setStudentId(request.getStudentId());
        entity.setInvoiceId(request.getInvoiceId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setAmount(request.getAmount());
        entity.setReason(request.getReason());
        entity.setIssuedBy(request.getIssuedBy());
        entity.setStatus(request.getStatus());
        entity.setIssuedDate(request.getIssuedDate());
        return entity;
    }

    public static FineResponse toResponse(Fine entity) {
        FineResponse response = new FineResponse();
        response.setId(entity.getId());
        response.setStudentId(entity.getStudentId());
        response.setInvoiceId(entity.getInvoiceId());
        response.setFeeTypeId(entity.getFeeTypeId());
        response.setAmount(entity.getAmount());
        response.setReason(entity.getReason());
        response.setIssuedBy(entity.getIssuedBy());
        response.setStatus(entity.getStatus());
        response.setIssuedDate(entity.getIssuedDate());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
