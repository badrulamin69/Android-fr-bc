package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.MarkRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MarkResponse;
import com.brilliantsofts.EliteUniversity.entity.Mark;

public class MarkMapper {
    public static Mark toEntity(MarkRequest request) {
        Mark entity = new Mark();
        entity.setMarksObtained(request.getMarksObtained());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setGrade(request.getGrade());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static MarkResponse toResponse(Mark entity) {
        MarkResponse response = new MarkResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setMarksObtained(entity.getMarksObtained());
        response.setTotalMarks(entity.getTotalMarks());
        response.setGrade(entity.getGrade());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getExam() != null) {
            response.setExamId(entity.getExam().getId());
            response.setExamName(entity.getExam().getName());
        }
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
        }
        if (entity.getSubject() != null) {
            response.setSubjectId(entity.getSubject().getId());
            response.setSubjectName(entity.getSubject().getName());
        }
        return response;
    }
}
