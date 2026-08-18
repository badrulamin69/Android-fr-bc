package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.LeaveRequestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LeaveRequestResponse;
import com.brilliantsofts.EliteUniversity.entity.LeaveRequest;

public class LeaveRequestMapper {
    public static LeaveRequest toEntity(LeaveRequestRequest request) {
        LeaveRequest entity = new LeaveRequest();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setLeaveType(request.getLeaveType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setReason(request.getReason());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static LeaveRequestResponse toResponse(LeaveRequest entity) {
        LeaveRequestResponse response = new LeaveRequestResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setEmployeeId(entity.getEmployeeId());
        response.setLeaveType(entity.getLeaveType());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setReason(entity.getReason());
        response.setStatus(entity.getStatus());
        response.setApprovedBy(entity.getApprovedBy());
        response.setApprovedAt(entity.getApprovedAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
