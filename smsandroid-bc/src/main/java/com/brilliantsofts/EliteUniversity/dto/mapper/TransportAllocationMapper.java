package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TransportAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.TransportAllocation;

public class TransportAllocationMapper {
    public static TransportAllocation toEntity(TransportAllocationRequest request) {
        TransportAllocation entity = new TransportAllocation();
        entity.setStudentId(request.getStudentId());
        entity.setRouteId(request.getRouteId());
        entity.setVehicleId(request.getVehicleId());
        entity.setPickupPoint(request.getPickupPoint());
        entity.setDropPoint(request.getDropPoint());
        entity.setMonthlyFee(request.getMonthlyFee());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static TransportAllocationResponse toResponse(TransportAllocation entity) {
        TransportAllocationResponse response = new TransportAllocationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setRouteId(entity.getRouteId());
        response.setVehicleId(entity.getVehicleId());
        response.setPickupPoint(entity.getPickupPoint());
        response.setDropPoint(entity.getDropPoint());
        response.setMonthlyFee(entity.getMonthlyFee());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
