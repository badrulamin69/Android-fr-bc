package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicationReviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicationReviewResponse;
import com.brilliantsofts.EliteUniversity.entity.ApplicationReview;

public class ApplicationReviewMapper {
    public static ApplicationReview toEntity(ApplicationReviewRequest request) {
        ApplicationReview entity = new ApplicationReview();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationId(request.getApplicationId());
        entity.setReviewerId(request.getReviewerId());
        entity.setStatus(request.getStatus());
        entity.setComments(request.getComments());
        entity.setRejectionReason(request.getRejectionReason());
        entity.setScore(request.getScore());
        entity.setReviewedAt(request.getReviewedAt());
        entity.setIsRecommended(request.getIsRecommended());
        return entity;
    }

    public static ApplicationReviewResponse toResponse(ApplicationReview entity) {
        ApplicationReviewResponse response = new ApplicationReviewResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setApplicationId(entity.getApplicationId());
        response.setReviewerId(entity.getReviewerId());
        response.setStatus(entity.getStatus());
        response.setComments(entity.getComments());
        response.setRejectionReason(entity.getRejectionReason());
        response.setScore(entity.getScore());
        response.setReviewedAt(entity.getReviewedAt());
        response.setIsRecommended(entity.getIsRecommended());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
