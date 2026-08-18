package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CertificateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CertificateResponse;
import com.brilliantsofts.EliteUniversity.entity.Certificate;

import java.time.LocalDateTime;
import java.util.UUID;

public class CertificateMapper {
    public static Certificate toEntity(CertificateRequest request) {
        Certificate entity = new Certificate();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setCertificateNumber(request.getCertificateNumber());
        entity.setCertificateType(request.getCertificateType());
        entity.setIssuedAt(LocalDateTime.now());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setPurpose(request.getPurpose());
        entity.setIsDownloaded(false);
        return entity;
    }

    public static CertificateResponse toResponse(Certificate entity) {
        CertificateResponse response = new CertificateResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setCertificateNumber(entity.getCertificateNumber());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setCertificateType(entity.getCertificateType());
        response.setIssuedAt(entity.getIssuedAt());
        response.setValidUntil(entity.getValidUntil());
        response.setStatus(entity.getStatus());
        response.setPurpose(entity.getPurpose());
        if (entity.getIssuedBy() != null) {
            response.setIssuedById(entity.getIssuedBy().getId());
        }
        response.setIsDownloaded(entity.getIsDownloaded());
        return response;
    }
}
