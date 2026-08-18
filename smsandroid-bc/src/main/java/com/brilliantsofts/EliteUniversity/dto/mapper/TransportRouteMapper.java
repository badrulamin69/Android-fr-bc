package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TransportRouteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportRouteResponse;
import com.brilliantsofts.EliteUniversity.entity.TransportRoute;

public class TransportRouteMapper {
    public static TransportRoute toEntity(TransportRouteRequest request) {
        TransportRoute entity = new TransportRoute();
        entity.setName(request.getName());
        entity.setRouteCode(request.getRouteCode());
        entity.setStartPoint(request.getStartPoint());
        entity.setEndPoint(request.getEndPoint());
        entity.setDistanceKm(request.getDistanceKm());
        entity.setFare(request.getFare());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return entity;
    }

    public static TransportRouteResponse toResponse(TransportRoute entity) {
        TransportRouteResponse response = new TransportRouteResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setRouteCode(entity.getRouteCode());
        response.setStartPoint(entity.getStartPoint());
        response.setEndPoint(entity.getEndPoint());
        response.setDistanceKm(entity.getDistanceKm());
        response.setFare(entity.getFare());
        response.setIsActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
