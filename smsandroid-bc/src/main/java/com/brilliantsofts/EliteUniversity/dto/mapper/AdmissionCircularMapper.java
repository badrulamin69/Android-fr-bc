package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCircularRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCircularResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCircular;

public class AdmissionCircularMapper {
    public static AdmissionCircular toEntity(AdmissionCircularRequest request) {
        AdmissionCircular entity = new AdmissionCircular();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEligibility(request.getEligibility());
        entity.setRequiredDocuments(request.getRequiredDocuments());
        entity.setAdmissionProcess(request.getAdmissionProcess());
        entity.setPublishDate(request.getPublishDate());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setAttachmentUrl(request.getAttachmentUrl());
        entity.setIsPublished(request.getIsPublished());
        entity.setSessionId(request.getSessionId());
        entity.setProgramId(request.getProgramId());
        return entity;
    }

    public static AdmissionCircularResponse toResponse(AdmissionCircular entity) {
        AdmissionCircularResponse response = new AdmissionCircularResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setEligibility(entity.getEligibility());
        response.setRequiredDocuments(entity.getRequiredDocuments());
        response.setAdmissionProcess(entity.getAdmissionProcess());
        response.setPublishDate(entity.getPublishDate());
        response.setValidUntil(entity.getValidUntil());
        response.setStatus(entity.getStatus());
        response.setAttachmentUrl(entity.getAttachmentUrl());
        response.setIsPublished(entity.getIsPublished());
        response.setSessionId(entity.getSessionId());
        response.setProgramId(entity.getProgramId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
