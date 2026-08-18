package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.HostelAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.HostelAllocation;

public class HostelAllocationMapper {
    public static HostelAllocation toEntity(HostelAllocationRequest request) {
        HostelAllocation entity = new HostelAllocation();
        entity.setStudentId(request.getStudentId());
        entity.setRoomId(request.getRoomId());
        entity.setAllocationDate(request.getAllocationDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());
        entity.setMonthlyRent(request.getMonthlyRent());
        return entity;
    }

    public static HostelAllocationResponse toResponse(HostelAllocation entity) {
        HostelAllocationResponse response = new HostelAllocationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setRoomId(entity.getRoomId());
        response.setAllocationDate(entity.getAllocationDate());
        response.setEndDate(entity.getEndDate());
        response.setStatus(entity.getStatus());
        response.setMonthlyRent(entity.getMonthlyRent());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
