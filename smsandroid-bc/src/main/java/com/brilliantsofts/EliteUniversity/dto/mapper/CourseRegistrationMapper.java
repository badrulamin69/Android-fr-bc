package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseRegistration;

import java.time.LocalDateTime;

public class CourseRegistrationMapper {
    public static CourseRegistration toEntity(CourseRegistrationRequest request) {
        CourseRegistration entity = new CourseRegistration();
        entity.setStatus(request.getStatus());
        entity.setCreditHours(request.getCreditHours());
        entity.setRemarks(request.getRemarks());
        entity.setRegistrationDate(LocalDateTime.now());
        return entity;
    }

    public static CourseRegistrationResponse toResponse(CourseRegistration entity) {
        CourseRegistrationResponse response = new CourseRegistrationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStatus(entity.getStatus());
        response.setRegistrationDate(entity.getRegistrationDate());
        response.setSelected(entity.isSelected());
        response.setCreditHours(entity.getCreditHours());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
        }
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
            response.setCourseCode(entity.getCourse().getCourseCode());
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
