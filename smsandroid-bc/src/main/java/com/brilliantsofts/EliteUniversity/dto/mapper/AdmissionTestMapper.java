package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTest;

public class AdmissionTestMapper {
    public static AdmissionTest toEntity(AdmissionTestRequest request) {
        AdmissionTest entity = new AdmissionTest();
        entity.setName(request.getName());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTestType(request.getTestType());
        entity.setTestDate(request.getTestDate());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassingMarks(request.getPassingMarks());
        entity.setNegativeMarking(request.getNegativeMarking());
        entity.setNegativeMarkValue(request.getNegativeMarkValue());
        entity.setExamCenter(request.getExamCenter());
        entity.setBuilding(request.getBuilding());
        entity.setRoom(request.getRoom());
        entity.setSeatCapacity(request.getSeatCapacity());
        entity.setInstructions(request.getInstructions());
        entity.setDescription(request.getDescription());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static AdmissionTestResponse toResponse(AdmissionTest entity) {
        AdmissionTestResponse response = new AdmissionTestResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setAcademicYear(entity.getAcademicYear());
        response.setSessionId(entity.getSessionId());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setProgramId(entity.getProgramId());
        response.setShift(entity.getShift());
        response.setTestType(entity.getTestType());
        response.setTestDate(entity.getTestDate());
        response.setStartTime(entity.getStartTime());
        response.setEndTime(entity.getEndTime());
        response.setDurationMinutes(entity.getDurationMinutes());
        response.setTotalMarks(entity.getTotalMarks());
        response.setPassingMarks(entity.getPassingMarks());
        response.setNegativeMarking(entity.getNegativeMarking());
        response.setNegativeMarkValue(entity.getNegativeMarkValue());
        response.setExamCenter(entity.getExamCenter());
        response.setBuilding(entity.getBuilding());
        response.setRoom(entity.getRoom());
        response.setSeatCapacity(entity.getSeatCapacity());
        response.setInstructions(entity.getInstructions());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
