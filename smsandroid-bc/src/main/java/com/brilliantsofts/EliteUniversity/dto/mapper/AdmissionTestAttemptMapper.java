package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestAttemptRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestAttemptResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestAttempt;

public class AdmissionTestAttemptMapper {
    public static AdmissionTestAttempt toEntity(AdmissionTestAttemptRequest request) {
        AdmissionTestAttempt entity = new AdmissionTestAttempt();
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        entity.setAnswers(request.getAnswers());
        entity.setTotalQuestions(request.getTotalQuestions());
        entity.setCorrectAnswers(request.getCorrectAnswers());
        entity.setScore(request.getScore());
        entity.setMaxScore(request.getMaxScore());
        entity.setPercentage(request.getPercentage());
        entity.setTimeTakenSeconds(request.getTimeTakenSeconds());
        entity.setStartedAt(request.getStartedAt());
        entity.setSubmittedAt(request.getSubmittedAt());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static AdmissionTestAttemptResponse toResponse(AdmissionTestAttempt entity) {
        AdmissionTestAttemptResponse response = new AdmissionTestAttemptResponse();
        response.setId(entity.getId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setTestId(entity.getTestId());
        response.setAnswers(entity.getAnswers());
        response.setTotalQuestions(entity.getTotalQuestions());
        response.setCorrectAnswers(entity.getCorrectAnswers());
        response.setScore(entity.getScore());
        response.setMaxScore(entity.getMaxScore());
        response.setPercentage(entity.getPercentage());
        response.setTimeTakenSeconds(entity.getTimeTakenSeconds());
        response.setStartedAt(entity.getStartedAt());
        response.setSubmittedAt(entity.getSubmittedAt());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
