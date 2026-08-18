package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentDocumentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentDocumentResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentDocument;

import java.time.LocalDateTime;
import java.util.UUID;

public class StudentDocumentMapper {
    public static StudentDocument toEntity(StudentDocumentRequest request) {
        StudentDocument entity = new StudentDocument();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setDocumentType(request.getDocumentType());
        entity.setDocumentName(request.getDocumentName());
        entity.setFileUrl(request.getFileUrl());
        entity.setFileSize(request.getFileSize());
        entity.setUploadedAt(LocalDateTime.now());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static StudentDocumentResponse toResponse(StudentDocument entity) {
        StudentDocumentResponse response = new StudentDocumentResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setDocumentType(entity.getDocumentType());
        response.setDocumentName(entity.getDocumentName());
        response.setFileUrl(entity.getFileUrl());
        response.setFileSize(entity.getFileSize());
        response.setUploadedAt(entity.getUploadedAt());
        response.setStatus(entity.getStatus());
        if (entity.getVerifiedBy() != null) {
            response.setVerifiedById(entity.getVerifiedBy().getId());
        }
        response.setVerifiedAt(entity.getVerifiedAt());
        response.setRemarks(entity.getRemarks());
        return response;
    }
}
