package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionConfirmationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionConfirmationResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionConfirmation;

public class AdmissionConfirmationMapper {
    public static AdmissionConfirmation toEntity(AdmissionConfirmationRequest request) {
        AdmissionConfirmation entity = new AdmissionConfirmation();
        entity.setConfirmationNumber(request.getConfirmationNumber());
        entity.setAllocationId(request.getAllocationId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setStatus(request.getStatus());
        entity.setDocumentsSubmitted(request.getDocumentsSubmitted());
        entity.setDocumentsVerified(request.getDocumentsVerified());
        entity.setDocumentsVerifiedBy(request.getDocumentsVerifiedBy());
        entity.setDocumentsVerifiedAt(request.getDocumentsVerifiedAt());
        entity.setDocumentRemarks(request.getDocumentRemarks());
        entity.setFeePaid(request.getFeePaid());
        entity.setFeeAmount(request.getFeeAmount());
        entity.setFeePaymentMethod(request.getFeePaymentMethod());
        entity.setFeeTransactionId(request.getFeeTransactionId());
        entity.setFeePaidAt(request.getFeePaidAt());
        entity.setConfirmedAt(request.getConfirmedAt());
        entity.setConfirmedBy(request.getConfirmedBy());
        entity.setRemarks(request.getRemarks());
        entity.setSessionId(request.getSessionId());
        return entity;
    }

    public static AdmissionConfirmationResponse toResponse(AdmissionConfirmation entity) {
        AdmissionConfirmationResponse response = new AdmissionConfirmationResponse();
        response.setId(entity.getId());
        response.setConfirmationNumber(entity.getConfirmationNumber());
        response.setAllocationId(entity.getAllocationId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setStatus(entity.getStatus());
        response.setDocumentsSubmitted(entity.getDocumentsSubmitted());
        response.setDocumentsVerified(entity.getDocumentsVerified());
        response.setDocumentsVerifiedBy(entity.getDocumentsVerifiedBy());
        response.setDocumentsVerifiedAt(entity.getDocumentsVerifiedAt());
        response.setDocumentRemarks(entity.getDocumentRemarks());
        response.setFeePaid(entity.getFeePaid());
        response.setFeeAmount(entity.getFeeAmount());
        response.setFeePaymentMethod(entity.getFeePaymentMethod());
        response.setFeeTransactionId(entity.getFeeTransactionId());
        response.setFeePaidAt(entity.getFeePaidAt());
        response.setConfirmedAt(entity.getConfirmedAt());
        response.setConfirmedBy(entity.getConfirmedBy());
        response.setRemarks(entity.getRemarks());
        response.setSessionId(entity.getSessionId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
