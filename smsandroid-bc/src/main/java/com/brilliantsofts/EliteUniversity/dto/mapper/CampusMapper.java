package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CampusRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CampusResponse;
import com.brilliantsofts.EliteUniversity.entity.Campus;

public class CampusMapper {
    public static Campus toEntity(CampusRequest request) {
        Campus entity = new Campus();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setCampusType(request.getCampusType());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setActive(request.isActive());
        return entity;
    }

    public static CampusResponse toResponse(Campus entity) {
        CampusResponse response = new CampusResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());
        response.setEmail(entity.getEmail());
        response.setCampusType(entity.getCampusType());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
