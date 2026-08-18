package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantChoiceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceResponse;
import com.brilliantsofts.EliteUniversity.entity.ApplicantChoice;

public class ApplicantChoiceMapper {
    public static ApplicantChoice toEntity(ApplicantChoiceRequest request) {
        ApplicantChoice entity = new ApplicantChoice();
        entity.setSubmissionId(request.getSubmissionId());
        entity.setPriority(request.getPriority());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setFacultyName(request.getFacultyName());
        entity.setDepartmentName(request.getDepartmentName());
        entity.setProgramName(request.getProgramName());
        entity.setShift(request.getShift());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static ApplicantChoiceResponse toResponse(ApplicantChoice entity) {
        ApplicantChoiceResponse response = new ApplicantChoiceResponse();
        response.setId(entity.getId());
        response.setSubmissionId(entity.getSubmissionId());
        response.setPriority(entity.getPriority());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setProgramId(entity.getProgramId());
        response.setFacultyName(entity.getFacultyName());
        response.setDepartmentName(entity.getDepartmentName());
        response.setProgramName(entity.getProgramName());
        response.setShift(entity.getShift());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
