package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionFeeCollectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionFeeCollectionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionFeeCollection;

public class AdmissionFeeCollectionMapper {
    public static AdmissionFeeCollection toEntity(AdmissionFeeCollectionRequest request) {
        AdmissionFeeCollection entity = new AdmissionFeeCollection();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setCandidateId(request.getCandidateId());
        entity.setAmount(request.getAmount());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setTransactionId(request.getTransactionId());
        entity.setStatus(request.getStatus());
        entity.setReceivedBy(request.getReceivedBy());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AdmissionFeeCollectionResponse toResponse(AdmissionFeeCollection entity) {
        AdmissionFeeCollectionResponse response = new AdmissionFeeCollectionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setCandidateId(entity.getCandidateId());
        response.setAmount(entity.getAmount());
        response.setPaymentMethod(entity.getPaymentMethod());
        response.setTransactionId(entity.getTransactionId());
        response.setStatus(entity.getStatus());
        response.setPaidAt(entity.getPaidAt());
        response.setReceivedBy(entity.getReceivedBy());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
