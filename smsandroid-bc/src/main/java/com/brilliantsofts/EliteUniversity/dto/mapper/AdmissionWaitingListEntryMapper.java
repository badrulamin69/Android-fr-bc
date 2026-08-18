package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListEntryResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingListEntry;

public class AdmissionWaitingListEntryMapper {
    public static AdmissionWaitingListEntry toEntity(AdmissionWaitingListEntryRequest request) {
        AdmissionWaitingListEntry entity = new AdmissionWaitingListEntry();
        entity.setWaitingListId(request.getWaitingListId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setRank(request.getRank());
        entity.setRollNumber(request.getRollNumber());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setApplicantName(request.getApplicantName());
        entity.setScore(request.getScore());
        entity.setTestMarks(request.getTestMarks());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setStatus(request.getStatus());
        entity.setIsPromoted(request.getIsPromoted());
        entity.setIsOffered(request.getIsOffered());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AdmissionWaitingListEntryResponse toResponse(AdmissionWaitingListEntry entity) {
        AdmissionWaitingListEntryResponse response = new AdmissionWaitingListEntryResponse();
        response.setId(entity.getId());
        response.setWaitingListId(entity.getWaitingListId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setRank(entity.getRank());
        response.setRollNumber(entity.getRollNumber());
        response.setApplicationNumber(entity.getApplicationNumber());
        response.setApplicantName(entity.getApplicantName());
        response.setScore(entity.getScore());
        response.setTestMarks(entity.getTestMarks());
        response.setTotalWeightedScore(entity.getTotalWeightedScore());
        response.setStatus(entity.getStatus());
        response.setIsPromoted(entity.getIsPromoted());
        response.setIsOffered(entity.getIsOffered());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
