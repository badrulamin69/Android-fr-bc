package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EmployeeAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.EmployeeAttendance;

public class EmployeeAttendanceMapper {
    public static EmployeeAttendance toEntity(EmployeeAttendanceRequest request) {
        EmployeeAttendance entity = new EmployeeAttendance();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setAttendanceDate(request.getAttendanceDate());
        entity.setStatus(request.getStatus());
        entity.setCheckIn(request.getCheckIn());
        entity.setCheckOut(request.getCheckOut());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static EmployeeAttendanceResponse toResponse(EmployeeAttendance entity) {
        EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setEmployeeId(entity.getEmployeeId());
        response.setAttendanceDate(entity.getAttendanceDate());
        response.setStatus(entity.getStatus());
        response.setCheckIn(entity.getCheckIn());
        response.setCheckOut(entity.getCheckOut());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
