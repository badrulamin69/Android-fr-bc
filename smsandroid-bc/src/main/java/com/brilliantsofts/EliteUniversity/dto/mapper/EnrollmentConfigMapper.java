package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.EnrollmentConfig;

public class EnrollmentConfigMapper {
    public static EnrollmentConfig toEntity(EnrollmentConfigRequest request) {
        EnrollmentConfig entity = new EnrollmentConfig();
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setLateEnrollmentDate(request.getLateEnrollmentDate());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        entity.setEnrollmentStatus(request.getEnrollmentStatus());
        entity.setActive(request.isActive());
        entity.setRequiresAdvisorApproval(request.isRequiresAdvisorApproval());
        entity.setRequiresPayment(request.isRequiresPayment());
        entity.setAllowLateEnrollment(request.isAllowLateEnrollment());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static EnrollmentConfigResponse toResponse(EnrollmentConfig entity) {
        EnrollmentConfigResponse response = new EnrollmentConfigResponse();
        response.setId(entity.getId());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setLateEnrollmentDate(entity.getLateEnrollmentDate());
        response.setMinCredits(entity.getMinCredits());
        response.setMaxCredits(entity.getMaxCredits());
        response.setEnrollmentStatus(entity.getEnrollmentStatus());
        response.setActive(entity.isActive());
        response.setClosed(entity.isClosed());
        response.setRequiresAdvisorApproval(entity.isRequiresAdvisorApproval());
        response.setRequiresPayment(entity.isRequiresPayment());
        response.setAllowLateEnrollment(entity.isAllowLateEnrollment());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getSemester() != null) {
            response.setSemesterId(entity.getSemester().getId());
            response.setSemesterName(entity.getSemester().getName());
        }
        if (entity.getAcademicSession() != null) {
            response.setAcademicSessionId(entity.getAcademicSession().getId());
            response.setAcademicSessionName(entity.getAcademicSession().getSessionName());
        }
        return response;
    }
}
