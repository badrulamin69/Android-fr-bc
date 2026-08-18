package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ExamScheduleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamScheduleResponse;
import com.brilliantsofts.EliteUniversity.entity.ExamSchedule;

public class ExamScheduleMapper {
    public static ExamSchedule toEntity(ExamScheduleRequest request) {
        ExamSchedule entity = new ExamSchedule();
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setVenue(request.getVenue());
        entity.setNotes(request.getNotes());
        return entity;
    }

    public static ExamScheduleResponse toResponse(ExamSchedule entity) {
        ExamScheduleResponse response = new ExamScheduleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setVenue(entity.getVenue());
        response.setNotes(entity.getNotes());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getExam() != null) {
            response.setExamId(entity.getExam().getId());
            response.setExamName(entity.getExam().getName());
        }
        return response;
    }
}
