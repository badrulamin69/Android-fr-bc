package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionResultResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionResult;

public class AdmissionResultMapper {
    public static AdmissionResult toEntity(AdmissionResultRequest request) {
        AdmissionResult entity = new AdmissionResult();
        entity.setAdmissionScore(request.getAdmissionScore());
        entity.setMeritPosition(request.getMeritPosition());
        entity.setResultStatus(request.getResultStatus());
        entity.setResultDate(request.getResultDate());
        return entity;
    }

    public static AdmissionResultResponse toResponse(AdmissionResult entity) {
        AdmissionResultResponse response = new AdmissionResultResponse();
        response.setId(entity.getId());
        response.setAdmissionScore(entity.getAdmissionScore());
        response.setMeritPosition(entity.getMeritPosition());
        response.setResultStatus(entity.getResultStatus());
        response.setResultDate(entity.getResultDate());
        if (entity.getApplicant() != null) {
            response.setApplicantId(entity.getApplicant().getId());
            response.setApplicantName(entity.getApplicant().getFullName());
        }
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
            response.setProgramName(entity.getProgram().getName());
        }
        return response;
    }
}
