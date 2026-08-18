package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResultResponse;
import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;

public class ExaminationResultMapper {
    public static ExaminationResult toEntity(ExaminationResultRequest request) {
        ExaminationResult entity = new ExaminationResult();
        entity.setMarks(request.getMarks());
        entity.setGradePoint(request.getGradePoint());
        entity.setGrade(request.getGrade());
        entity.setCredit(request.getCredit());
        return entity;
    }

    public static ExaminationResultResponse toResponse(ExaminationResult entity) {
        ExaminationResultResponse response = new ExaminationResultResponse();
        response.setId(entity.getId());
        response.setMarks(entity.getMarks());
        response.setGradePoint(entity.getGradePoint());
        response.setGrade(entity.getGrade());
        response.setCredit(entity.getCredit());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
        }
        if (entity.getExamination() != null) {
            response.setExaminationId(entity.getExamination().getId());
            response.setExaminationName(entity.getExamination().getExaminationName());
        }
        return response;
    }
}
