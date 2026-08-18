package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCampaignRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCampaignResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCampaign;

public class AdmissionCampaignMapper {
    public static AdmissionCampaign toEntity(AdmissionCampaignRequest request) {
        AdmissionCampaign entity = new AdmissionCampaign();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setDescription(request.getDescription());
        entity.setBudget(request.getBudget());
        entity.setSpent(request.getSpent());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());
        entity.setTargetAudience(request.getTargetAudience());
        entity.setChannels(request.getChannels());
        entity.setApplicationsGenerated(request.getApplicationsGenerated());
        entity.setEnrollmentsConverted(request.getEnrollmentsConverted());
        entity.setNotes(request.getNotes());
        entity.setSessionId(request.getSessionId());
        return entity;
    }

    public static AdmissionCampaignResponse toResponse(AdmissionCampaign entity) {
        AdmissionCampaignResponse response = new AdmissionCampaignResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setType(entity.getType());
        response.setDescription(entity.getDescription());
        response.setBudget(entity.getBudget());
        response.setSpent(entity.getSpent());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setStatus(entity.getStatus());
        response.setTargetAudience(entity.getTargetAudience());
        response.setChannels(entity.getChannels());
        response.setApplicationsGenerated(entity.getApplicationsGenerated());
        response.setEnrollmentsConverted(entity.getEnrollmentsConverted());
        response.setNotes(entity.getNotes());
        response.setSessionId(entity.getSessionId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
