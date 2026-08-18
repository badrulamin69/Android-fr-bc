package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterRegistration;

import java.time.LocalDateTime;

public class SemesterRegistrationMapper {
    public static SemesterRegistration toEntity(SemesterRegistrationRequest request) {
        SemesterRegistration entity = new SemesterRegistration();
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setRegistrationDate(LocalDateTime.now());
        return entity;
    }

    public static SemesterRegistrationResponse toResponse(SemesterRegistration entity) {
        SemesterRegistrationResponse response = new SemesterRegistrationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setRegistrationDate(entity.getRegistrationDate());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
        }
        if (entity.getSemester() != null) {
            response.setSemesterId(entity.getSemester().getId());
            response.setSemesterName(entity.getSemester().getName());
        }
        if (entity.getBatch() != null) {
            response.setBatchId(entity.getBatch().getId());
            response.setBatchName(entity.getBatch().getName());
        }
        if (entity.getApprovedBy() != null) {
            response.setApprovedById(entity.getApprovedBy().getId());
            response.setApprovedByName(entity.getApprovedBy().getFullName());
        }
        return response;
    }
}
