package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResultResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestResult;

public class AdmissionTestResultMapper {
    public static AdmissionTestResult toEntity(AdmissionTestResultRequest request) {
        AdmissionTestResult entity = new AdmissionTestResult();
        entity.setWrittenMarks(request.getWrittenMarks());
        entity.setMcqMarks(request.getMcqMarks());
        entity.setVivaMarks(request.getVivaMarks());
        entity.setWrittenMax(request.getWrittenMax());
        entity.setMcqMax(request.getMcqMax());
        entity.setVivaMax(request.getVivaMax());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        return entity;
    }

    public static AdmissionTestResultResponse toResponse(AdmissionTestResult entity) {
        AdmissionTestResultResponse response = new AdmissionTestResultResponse();
        response.setId(entity.getId());
        response.setWrittenMarks(entity.getWrittenMarks());
        response.setMcqMarks(entity.getMcqMarks());
        response.setVivaMarks(entity.getVivaMarks());
        response.setWrittenMax(entity.getWrittenMax());
        response.setMcqMax(entity.getMcqMax());
        response.setVivaMax(entity.getVivaMax());
        response.setTotalWeightedScore(entity.getTotalWeightedScore());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setRegistrationId(entity.getRegistrationId());
        response.setTestId(entity.getTestId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
