package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionAttendance;

public class AdmissionAttendanceMapper {
    public static AdmissionAttendance toEntity(AdmissionAttendanceRequest request) {
        AdmissionAttendance entity = new AdmissionAttendance();
        entity.setTestId(request.getTestId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAttemptId(request.getAttemptId());
        entity.setStatus(request.getStatus());
        entity.setCheckInTime(request.getCheckInTime());
        entity.setCheckOutTime(request.getCheckOutTime());
        entity.setMarkedById(request.getMarkedById());
        entity.setRemarks(request.getRemarks());
        entity.setQrScanned(request.getQrScanned());
        return entity;
    }

    public static AdmissionAttendanceResponse toResponse(AdmissionAttendance entity) {
        AdmissionAttendanceResponse response = new AdmissionAttendanceResponse();
        response.setId(entity.getId());
        response.setTestId(entity.getTestId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setAttemptId(entity.getAttemptId());
        response.setStatus(entity.getStatus());
        response.setCheckInTime(entity.getCheckInTime());
        response.setCheckOutTime(entity.getCheckOutTime());
        response.setMarkedById(entity.getMarkedById());
        response.setRemarks(entity.getRemarks());
        response.setQrScanned(entity.getQrScanned());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
