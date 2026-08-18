package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.PaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PaymentResponse;
import com.brilliantsofts.EliteUniversity.entity.Payment;

public class PaymentMapper {
    public static Payment toEntity(PaymentRequest request) {
        Payment entity = new Payment();
        entity.setAmount(request.getAmount());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setStatus(request.getStatus());
        entity.setTransactionId(request.getTransactionId());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());
        return entity;
    }

    public static PaymentResponse toResponse(Payment entity) {
        PaymentResponse response = new PaymentResponse();
        response.setId(entity.getId());
        response.setPaymentNumber(entity.getPaymentNumber());
        response.setAmount(entity.getAmount());
        response.setPaymentMethod(entity.getPaymentMethod());
        response.setStatus(entity.getStatus());
        response.setTransactionId(entity.getTransactionId());
        response.setGatewayResponse(entity.getGatewayResponse());
        response.setPaymentDate(entity.getPaymentDate());
        response.setCreatedBy(entity.getCreatedBy());
        response.setNotes(entity.getNotes());
        response.setReceiptUrl(entity.getReceiptUrl());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName() != null ? entity.getStudent().getFullName() : "Student #" + entity.getStudent().getId());
            response.setStudentCode(entity.getStudent().getStudentId());
        }
        if (entity.getApplicant() != null) {
            response.setApplicantId(entity.getApplicant().getId());
            response.setApplicantName(entity.getApplicant().getFullName() != null ? entity.getApplicant().getFullName() : "Applicant #" + entity.getApplicant().getId());
        }
        return response;
    }
}
