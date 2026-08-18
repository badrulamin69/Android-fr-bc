package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionApplicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionApplicationResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionApplication;

public class AdmissionApplicationMapper {
    public static AdmissionApplication toEntity(AdmissionApplicationRequest request) {
        AdmissionApplication entity = new AdmissionApplication();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setCandidateId(request.getCandidateId());
        entity.setCircularId(request.getCircularId());
        entity.setSessionId(request.getSessionId());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setCampusId(request.getCampusId());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setSubmittedAt(request.getSubmittedAt());
        entity.setIsSubmitted(request.getIsSubmitted());
        entity.setIsVerified(request.getIsVerified());
        entity.setExamId(request.getExamId());
        entity.setTestScore(request.getTestScore());
        entity.setMeritScore(request.getMeritScore());
        entity.setMeritPosition(request.getMeritPosition());
        return entity;
    }

    public static AdmissionApplicationResponse toResponse(AdmissionApplication entity) {
        if (entity == null) return null;
        AdmissionApplicationResponse response = new AdmissionApplicationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setApplicationNumber(entity.getApplicationNumber());
        response.setCandidateId(entity.getCandidateId());
        response.setCircularId(entity.getCircularId());
        response.setSessionId(entity.getSessionId());
        response.setProgramId(entity.getProgramId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setCampusId(entity.getCampusId());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setSubmittedAt(entity.getSubmittedAt());
        response.setIsSubmitted(entity.getIsSubmitted());
        response.setIsVerified(entity.getIsVerified());
        response.setExamId(entity.getExamId());
        response.setTestScore(entity.getTestScore());
        response.setMeritScore(entity.getMeritScore());
        response.setMeritPosition(entity.getMeritPosition());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
