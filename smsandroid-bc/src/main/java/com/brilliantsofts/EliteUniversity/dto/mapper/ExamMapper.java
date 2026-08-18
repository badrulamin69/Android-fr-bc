package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ExamRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamResponse;
import com.brilliantsofts.EliteUniversity.entity.Exam;

public class ExamMapper {
    public static Exam toEntity(ExamRequest request) {
        Exam entity = new Exam();
        entity.setName(request.getName());
        entity.setExamType(request.getExamType());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassingMarks(request.getPassingMarks());
        entity.setExamDate(request.getExamDate());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static ExamResponse toResponse(Exam entity) {
        ExamResponse response = new ExamResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setExamType(entity.getExamType());
        response.setTotalMarks(entity.getTotalMarks());
        response.setPassingMarks(entity.getPassingMarks());
        response.setExamDate(entity.getExamDate());
        response.setDurationMinutes(entity.getDurationMinutes());
        response.setDescription(entity.getDescription());
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
        return response;
    }
}
