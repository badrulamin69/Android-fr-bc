package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterEnrollment;
import java.time.LocalDateTime;

public class SemesterEnrollmentMapper {
    public static SemesterEnrollment toEntity(SemesterEnrollmentRequest request) {
        SemesterEnrollment entity = new SemesterEnrollment();
        entity.setStudentId(request.getStudentId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setProgramId(request.getProgramId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setAdvisorId(request.getAdvisorId());
        entity.setStatus(request.getStatus());
        entity.setRegisteredCredits(request.getRegisteredCredits());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        entity.setRemarks(request.getRemarks());
        entity.setEnrollmentType(request.getEnrollmentType());
        entity.setEnrollmentDate(LocalDateTime.now());
        return entity;
    }

    public static SemesterEnrollmentResponse toResponse(SemesterEnrollment entity) {
        SemesterEnrollmentResponse response = new SemesterEnrollmentResponse();
        response.setId(entity.getId());
        response.setEnrollmentNumber(entity.getEnrollmentNumber());
        response.setStudentId(entity.getStudentId());
        response.setSemesterId(entity.getSemesterId());
        response.setBatchId(entity.getBatchId());
        response.setProgramId(entity.getProgramId());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setAdvisorId(entity.getAdvisorId());
        response.setEnrollmentDate(entity.getEnrollmentDate());
        response.setStatus(entity.getStatus());
        response.setRegisteredCredits(entity.getRegisteredCredits());
        response.setMinCredits(entity.getMinCredits());
        response.setMaxCredits(entity.getMaxCredits());
        response.setAdvisorStatus(entity.getAdvisorStatus());
        response.setAdvisorComments(entity.getAdvisorComments());
        response.setAdvisorApprovedAt(entity.getAdvisorApprovedAt());
        response.setPaymentStatus(entity.getPaymentStatus());
        response.setPaymentAmount(entity.getPaymentAmount());
        response.setPaymentReference(entity.getPaymentReference());
        response.setPaymentDate(entity.getPaymentDate());
        response.setFinalized(entity.isFinalized());
        response.setFinalizedAt(entity.getFinalizedAt());
        response.setRemarks(entity.getRemarks());
        response.setActive(entity.isActive());
        response.setLateEnrollment(entity.isLateEnrollment());
        response.setEnrollmentType(entity.getEnrollmentType());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
