package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentAttendance;

import java.util.UUID;

public class StudentAttendanceMapper {
    public static StudentAttendance toEntity(StudentAttendanceRequest request) {
        if (request == null) return null;
        StudentAttendance entity = new StudentAttendance();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setAttendanceDate(request.getAttendanceDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static StudentAttendanceResponse toResponse(StudentAttendance entity) {
        if (entity == null) return null;
        StudentAttendanceResponse response = new StudentAttendanceResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
            if (entity.getStudent().getProgram() != null && entity.getStudent().getProgram().getDepartment() != null) {
                response.setDepartmentName(entity.getStudent().getProgram().getDepartment().getName());
            }
        }
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
            response.setCourseCode(entity.getCourse().getCourseCode());
        }
        if (entity.getSemester() != null) {
            response.setSemesterId(entity.getSemester().getId());
            response.setSemesterName(entity.getSemester().getSessionName());
        }
        response.setAttendanceDate(entity.getAttendanceDate());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCheckInTime(entity.getCheckInTime());
        response.setCheckOutTime(entity.getCheckOutTime());
        if (entity.getRecordedBy() != null) {
            response.setRecordedById(entity.getRecordedBy().getId());
        }
        return response;
    }
}
