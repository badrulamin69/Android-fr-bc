package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AssignmentSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentSubmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.AssignmentSubmission;

public class AssignmentSubmissionMapper {
    public static AssignmentSubmission toEntity(AssignmentSubmissionRequest request) {
        AssignmentSubmission entity = new AssignmentSubmission();
        entity.setSubmissionDate(request.getSubmissionDate());
        entity.setFileUrl(request.getFileUrl());
        entity.setNotes(request.getNotes());
        entity.setMarksObtained(request.getMarksObtained());
        entity.setFeedback(request.getFeedback());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static AssignmentSubmissionResponse toResponse(AssignmentSubmission entity) {
        AssignmentSubmissionResponse response = new AssignmentSubmissionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setSubmissionDate(entity.getSubmissionDate());
        response.setFileUrl(entity.getFileUrl());
        response.setNotes(entity.getNotes());
        response.setMarksObtained(entity.getMarksObtained());
        response.setFeedback(entity.getFeedback());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getAssignment() != null) {
            response.setAssignmentId(entity.getAssignment().getId());
            response.setAssignmentTitle(entity.getAssignment().getTitle());
        }
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
        }
        return response;
    }
}
