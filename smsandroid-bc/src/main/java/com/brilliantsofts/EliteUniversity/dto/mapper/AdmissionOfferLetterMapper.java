package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionOfferLetterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionOfferLetterResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionOfferLetter;

public class AdmissionOfferLetterMapper {
    public static AdmissionOfferLetter toEntity(AdmissionOfferLetterRequest request) {
        AdmissionOfferLetter entity = new AdmissionOfferLetter();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setLetterNumber(request.getLetterNumber());
        entity.setApplicationId(request.getApplicationId());
        entity.setMeritListEntryId(request.getMeritListEntryId());
        entity.setIssuedAt(request.getIssuedAt());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setLetterContent(request.getLetterContent());
        entity.setConditions(request.getConditions());
        entity.setRemarks(request.getRemarks());
        entity.setIssuedById(request.getIssuedById());
        entity.setAcceptedAt(request.getAcceptedAt());
        entity.setDeclinedAt(request.getDeclinedAt());
        entity.setDeclineReason(request.getDeclineReason());
        entity.setIsDownloaded(request.getIsDownloaded());
        return entity;
    }

    public static AdmissionOfferLetterResponse toResponse(AdmissionOfferLetter entity) {
        AdmissionOfferLetterResponse response = new AdmissionOfferLetterResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setLetterNumber(entity.getLetterNumber());
        response.setApplicationId(entity.getApplicationId());
        response.setMeritListEntryId(entity.getMeritListEntryId());
        response.setIssuedAt(entity.getIssuedAt());
        response.setValidUntil(entity.getValidUntil());
        response.setStatus(entity.getStatus());
        response.setLetterContent(entity.getLetterContent());
        response.setConditions(entity.getConditions());
        response.setRemarks(entity.getRemarks());
        response.setIssuedById(entity.getIssuedById());
        response.setAcceptedAt(entity.getAcceptedAt());
        response.setDeclinedAt(entity.getDeclinedAt());
        response.setDeclineReason(entity.getDeclineReason());
        response.setIsDownloaded(entity.getIsDownloaded());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
