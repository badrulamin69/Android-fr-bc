package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Assignment;

public class AssignmentMapper {
    public static Assignment toEntity(AssignmentRequest request) {
        Assignment entity = new Assignment();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDueDate(request.getDueDate());
        entity.setMaxMarks(request.getMaxMarks());
        return entity;
    }

    public static AssignmentResponse toResponse(Assignment entity) {
        AssignmentResponse response = new AssignmentResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setDueDate(entity.getDueDate());
        response.setMaxMarks(entity.getMaxMarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        if (entity.getSubject() != null) {
            response.setSubjectId(entity.getSubject().getId());
            response.setSubjectName(entity.getSubject().getName());
        }
        if (entity.getSection() != null) {
            response.setSectionId(entity.getSection().getId());
            response.setSectionName(entity.getSection().getName());
        }
        if (entity.getAdministration() != null) {
            response.setAdministrationId(entity.getAdministration().getId());
            response.setAdministrationName(entity.getAdministration().getFullName());
        }
        return response;
    }
}
