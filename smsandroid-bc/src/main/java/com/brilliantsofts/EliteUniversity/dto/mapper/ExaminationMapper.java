package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResponse;
import com.brilliantsofts.EliteUniversity.entity.Examination;

public class ExaminationMapper {
    public static Examination toEntity(ExaminationRequest request) {
        Examination entity = new Examination();
        entity.setExaminationName(request.getExaminationName());
        entity.setSemester(request.getSemester());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassMarks(request.getPassMarks());
        entity.setExaminationDate(request.getExaminationDate());
        return entity;
    }

    public static ExaminationResponse toResponse(Examination entity) {
        ExaminationResponse response = new ExaminationResponse();
        response.setId(entity.getId());
        response.setExaminationName(entity.getExaminationName());
        response.setSemester(entity.getSemester());
        response.setTotalMarks(entity.getTotalMarks());
        response.setPassMarks(entity.getPassMarks());
        response.setExaminationDate(entity.getExaminationDate());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        return response;
    }
}
