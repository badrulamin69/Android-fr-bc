package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.SeatAllocation;

public class SeatAllocationMapper {
    public static SeatAllocation toEntity(SeatAllocationRequest request) {
        SeatAllocation entity = new SeatAllocation();
        entity.setTestId(request.getTestId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setCenterId(request.getCenterId());
        entity.setCenterName(request.getCenterName());
        entity.setBuildingName(request.getBuildingName());
        entity.setRoomName(request.getRoomName());
        entity.setSeatNumber(request.getSeatNumber());
        entity.setRollNumber(request.getRollNumber());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static SeatAllocationResponse toResponse(SeatAllocation entity) {
        SeatAllocationResponse response = new SeatAllocationResponse();
        response.setId(entity.getId());
        response.setTestId(entity.getTestId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setCenterId(entity.getCenterId());
        response.setCenterName(entity.getCenterName());
        response.setBuildingName(entity.getBuildingName());
        response.setRoomName(entity.getRoomName());
        response.setSeatNumber(entity.getSeatNumber());
        response.setRollNumber(entity.getRollNumber());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
