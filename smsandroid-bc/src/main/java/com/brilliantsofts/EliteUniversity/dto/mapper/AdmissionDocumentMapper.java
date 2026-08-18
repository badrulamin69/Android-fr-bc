package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionDocumentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionDocumentResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionDocument;

public class AdmissionDocumentMapper {
    public static AdmissionDocument toEntity(AdmissionDocumentRequest request) {
        AdmissionDocument entity = new AdmissionDocument();
        entity.setConfirmationId(request.getConfirmationId());
        entity.setDocumentType(request.getDocumentType());
        entity.setDocumentName(request.getDocumentName());
        entity.setFileUrl(request.getFileUrl());
        entity.setFileSize(request.getFileSize());
        entity.setStatus(request.getStatus());
        entity.setVerifiedBy(request.getVerifiedBy());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AdmissionDocumentResponse toResponse(AdmissionDocument entity) {
        AdmissionDocumentResponse response = new AdmissionDocumentResponse();
        response.setId(entity.getId());
        response.setConfirmationId(entity.getConfirmationId());
        response.setDocumentType(entity.getDocumentType());
        response.setDocumentName(entity.getDocumentName());
        response.setFileUrl(entity.getFileUrl());
        response.setFileSize(entity.getFileSize());
        response.setStatus(entity.getStatus());
        response.setVerifiedBy(entity.getVerifiedBy());
        response.setVerifiedAt(entity.getVerifiedAt());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
