package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantResponse;
import com.brilliantsofts.EliteUniversity.entity.Applicant;

public class ApplicantMapper {
    public static Applicant toEntity(ApplicantRequest request) {
        Applicant entity = new Applicant();
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setApplicationLevel(request.getApplicationLevel());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static ApplicantResponse toResponse(Applicant entity) {
        ApplicantResponse response = new ApplicantResponse();
        response.setId(entity.getId());
        response.setApplicationNumber(entity.getApplicationNumber());
        response.setFullName(entity.getFullName());
        response.setPhone(entity.getPhone());
        response.setAddress(entity.getAddress());
        response.setApplicationLevel(entity.getApplicationLevel());
        response.setStatus(entity.getStatus());
        if (entity.getUser() != null) response.setUserId(entity.getUser().getId());
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
            response.setProgramName(entity.getProgram().getName());
        }
        return response;
    }
}
