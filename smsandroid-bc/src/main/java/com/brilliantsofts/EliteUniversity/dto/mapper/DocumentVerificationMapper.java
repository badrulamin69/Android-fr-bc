package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DocumentVerificationResponse;
import com.brilliantsofts.EliteUniversity.entity.DocumentVerification;
import java.time.LocalDateTime;

public class DocumentVerificationMapper {
    public static DocumentVerification toEntity(DocumentVerificationRequest request) {
        DocumentVerification entity = new DocumentVerification();
        entity.setAdmissionCandidateId(request.getAdmissionCandidateId());
        entity.setDocumentType(request.getDocumentType());
        entity.setDocumentNumber(request.getDocumentNumber());
        entity.setVerified(request.isVerified());
        entity.setVerifiedBy(request.getVerifiedBy());
        entity.setRemarks(request.getRemarks());
        if (request.isVerified()) entity.setVerificationDate(LocalDateTime.now());
        return entity;
    }

    public static DocumentVerificationResponse toResponse(DocumentVerification entity) {
        DocumentVerificationResponse response = new DocumentVerificationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setAdmissionCandidateId(entity.getAdmissionCandidateId());
        response.setDocumentType(entity.getDocumentType());
        response.setDocumentNumber(entity.getDocumentNumber());
        response.setVerified(entity.isVerified());
        response.setVerifiedBy(entity.getVerifiedBy());
        response.setVerificationDate(entity.getVerificationDate());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}