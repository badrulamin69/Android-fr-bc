package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.BuildingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BuildingResponse;
import com.brilliantsofts.EliteUniversity.entity.Building;

public class BuildingMapper {
    public static Building toEntity(BuildingRequest request) {
        Building entity = new Building();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setAddress(request.getAddress());
        entity.setTotalFloors(request.getTotalFloors());
        entity.setTotalRooms(request.getTotalRooms());
        entity.setContactPerson(request.getContactPerson());
        entity.setContactPhone(request.getContactPhone());
        entity.setActive(request.isActive());
        return entity;
    }

    public static BuildingResponse toResponse(Building entity) {
        BuildingResponse response = new BuildingResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setAddress(entity.getAddress());
        response.setTotalFloors(entity.getTotalFloors());
        response.setTotalRooms(entity.getTotalRooms());
        response.setContactPerson(entity.getContactPerson());
        response.setContactPhone(entity.getContactPhone());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
