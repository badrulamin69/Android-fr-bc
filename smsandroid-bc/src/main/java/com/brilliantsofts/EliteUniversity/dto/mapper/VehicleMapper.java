package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.VehicleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VehicleResponse;
import com.brilliantsofts.EliteUniversity.entity.Vehicle;

public class VehicleMapper {
    public static Vehicle toEntity(VehicleRequest request) {
        Vehicle entity = new Vehicle();
        entity.setVehicleNumber(request.getVehicleNumber());
        entity.setVehicleType(request.getVehicleType());
        entity.setCapacity(request.getCapacity());
        entity.setDriverName(request.getDriverName());
        entity.setDriverPhone(request.getDriverPhone());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return entity;
    }

    public static VehicleResponse toResponse(Vehicle entity) {
        VehicleResponse response = new VehicleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setVehicleNumber(entity.getVehicleNumber());
        response.setVehicleType(entity.getVehicleType());
        response.setCapacity(entity.getCapacity());
        response.setDriverName(entity.getDriverName());
        response.setDriverPhone(entity.getDriverPhone());
        response.setIsActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
