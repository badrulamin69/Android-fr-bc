package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ClassRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassRoutineResponse;
import com.brilliantsofts.EliteUniversity.entity.ClassRoutine;

public class ClassRoutineMapper {
    public static ClassRoutine toEntity(ClassRoutineRequest request) {
        ClassRoutine entity = new ClassRoutine();
        entity.setSubjectId(request.getSubjectId());
        entity.setAdministrationId(request.getAdministrationId());
        entity.setSectionId(request.getSectionId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setTimeSlotId(request.getTimeSlotId());
        entity.setClassroomId(request.getClassroomId());
        entity.setDayOfWeek(request.getDayOfWeek());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setRoom(request.getRoom());
        entity.setBuilding(request.getBuilding());
        entity.setClassType(request.getClassType());
        entity.setShift(request.getShift());
        entity.setActive(request.isActive());
        return entity;
    }

    public static ClassRoutineResponse toResponse(ClassRoutine entity) {
        ClassRoutineResponse response = new ClassRoutineResponse();
        response.setId(entity.getId());
        response.setSubjectId(entity.getSubjectId());
        response.setAdministrationId(entity.getAdministrationId());
        response.setSectionId(entity.getSectionId());
        response.setSemesterId(entity.getSemesterId());
        response.setBatchId(entity.getBatchId());
        response.setTimeSlotId(entity.getTimeSlotId());
        response.setClassroomId(entity.getClassroomId());
        response.setDayOfWeek(entity.getDayOfWeek());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setRoom(entity.getRoom());
        response.setBuilding(entity.getBuilding());
        response.setClassType(entity.getClassType());
        response.setShift(entity.getShift());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
