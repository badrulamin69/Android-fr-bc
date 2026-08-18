package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentEnrollment;

public class StudentEnrollmentMapper {
    public static StudentEnrollment toEntity(StudentEnrollmentRequest request) {
        StudentEnrollment entity = new StudentEnrollment();
        entity.setStudentId(request.getStudentId());
        entity.setBatchId(request.getBatchId());
        entity.setSectionId(request.getSectionId());
        entity.setEnrollmentDate(request.getEnrollmentDate());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static StudentEnrollmentResponse toResponse(StudentEnrollment entity) {
        StudentEnrollmentResponse response = new StudentEnrollmentResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setBatchId(entity.getBatchId());
        response.setSectionId(entity.getSectionId());
        response.setEnrollmentDate(entity.getEnrollmentDate());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
