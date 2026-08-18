package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRoutineResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterRoutine;

public class SemesterRoutineMapper {
    public static SemesterRoutine toEntity(SemesterRoutineRequest request) {
        SemesterRoutine entity = new SemesterRoutine();
        entity.setSemesterId(request.getSemesterId());
        entity.setProgramId(request.getProgramId());
        entity.setBatchId(request.getBatchId());
        entity.setDescription(request.getDescription());
        entity.setTotalWeeks(request.getTotalWeeks());
        entity.setMidtermWeek(request.getMidtermWeek());
        entity.setFinalExamWeek(request.getFinalExamWeek());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActive(request.isActive());
        return entity;
    }

    public static SemesterRoutineResponse toResponse(SemesterRoutine entity) {
        SemesterRoutineResponse response = new SemesterRoutineResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setSemesterId(entity.getSemesterId());
        response.setProgramId(entity.getProgramId());
        response.setBatchId(entity.getBatchId());
        response.setDescription(entity.getDescription());
        response.setTotalWeeks(entity.getTotalWeeks());
        response.setMidtermWeek(entity.getMidtermWeek());
        response.setFinalExamWeek(entity.getFinalExamWeek());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
