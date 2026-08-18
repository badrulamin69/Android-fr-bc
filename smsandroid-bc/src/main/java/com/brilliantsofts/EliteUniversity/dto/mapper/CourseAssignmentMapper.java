package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CourseAssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseAssignmentResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseAssignment;

public class CourseAssignmentMapper {
    public static CourseAssignment toEntity(CourseAssignmentRequest request) {
        CourseAssignment entity = new CourseAssignment();
        entity.setCourseId(request.getCourseId());
        entity.setSubjectId(request.getSubjectId());
        entity.setAdministrationId(request.getAdministrationId());
        entity.setSemester(request.getSemester());
        return entity;
    }

    public static CourseAssignmentResponse toResponse(CourseAssignment entity) {
        CourseAssignmentResponse response = new CourseAssignmentResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setCourseId(entity.getCourseId());
        response.setSubjectId(entity.getSubjectId());
        response.setAdministrationId(entity.getAdministrationId());
        response.setSemester(entity.getSemester());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}