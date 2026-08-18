package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Enrollment;

public class EnrollmentMapper {
    public static Enrollment toEntity(EnrollmentRequest request) {
        Enrollment entity = new Enrollment();
        entity.setEnrollmentDate(request.getEnrollmentDate());
        entity.setSemester(request.getSemester());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static EnrollmentResponse toResponse(Enrollment entity) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(entity.getId());
        response.setEnrollmentDate(entity.getEnrollmentDate());
        response.setSemester(entity.getSemester());
        response.setStatus(entity.getStatus());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
        }
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        return response;
    }
}
