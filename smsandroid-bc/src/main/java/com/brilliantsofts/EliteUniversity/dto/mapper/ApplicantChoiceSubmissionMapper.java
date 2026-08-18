package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantChoiceSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceSubmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.ApplicantChoiceSubmission;

public class ApplicantChoiceSubmissionMapper {
    public static ApplicantChoiceSubmission toEntity(ApplicantChoiceSubmissionRequest request) {
        ApplicantChoiceSubmission entity = new ApplicantChoiceSubmission();
        entity.setRegistrationId(request.getRegistrationId());
        entity.setConfigId(request.getConfigId());
        entity.setMeritListEntryId(request.getMeritListEntryId());
        entity.setSubmissionId(request.getSubmissionId());
        entity.setTotalChoices(request.getTotalChoices());
        entity.setStatus(request.getStatus());
        entity.setApplicantName(request.getApplicantName());
        entity.setMeritRank(request.getMeritRank());
        entity.setMeritScore(request.getMeritScore());
        return entity;
    }

    public static ApplicantChoiceSubmissionResponse toResponse(ApplicantChoiceSubmission entity) {
        ApplicantChoiceSubmissionResponse response = new ApplicantChoiceSubmissionResponse();
        response.setId(entity.getId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setConfigId(entity.getConfigId());
        response.setMeritListEntryId(entity.getMeritListEntryId());
        response.setSubmissionId(entity.getSubmissionId());
        response.setTotalChoices(entity.getTotalChoices());
        response.setStatus(entity.getStatus());
        response.setSubmittedAt(entity.getSubmittedAt());
        response.setLockedAt(entity.getLockedAt());
        response.setApplicantName(entity.getApplicantName());
        response.setMeritRank(entity.getMeritRank());
        response.setMeritScore(entity.getMeritScore());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
