package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionInterviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionInterviewResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionInterview;

public class AdmissionInterviewMapper {
    public static AdmissionInterview toEntity(AdmissionInterviewRequest request) {
        AdmissionInterview entity = new AdmissionInterview();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationId(request.getApplicationId());
        entity.setInterviewerId(request.getInterviewerId());
        entity.setScheduledAt(request.getScheduledAt());
        entity.setCompletedAt(request.getCompletedAt());
        entity.setInterviewType(request.getInterviewType());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setScore(request.getScore());
        entity.setMaxScore(request.getMaxScore());
        entity.setStrengths(request.getStrengths());
        entity.setWeaknesses(request.getWeaknesses());
        entity.setIsRecommended(request.getIsRecommended());
        return entity;
    }

    public static AdmissionInterviewResponse toResponse(AdmissionInterview entity) {
        AdmissionInterviewResponse response = new AdmissionInterviewResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setApplicationId(entity.getApplicationId());
        response.setInterviewerId(entity.getInterviewerId());
        response.setScheduledAt(entity.getScheduledAt());
        response.setCompletedAt(entity.getCompletedAt());
        response.setInterviewType(entity.getInterviewType());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setScore(entity.getScore());
        response.setMaxScore(entity.getMaxScore());
        response.setStrengths(entity.getStrengths());
        response.setWeaknesses(entity.getWeaknesses());
        response.setIsRecommended(entity.getIsRecommended());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
