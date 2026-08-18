package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.OrientationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OrientationResponse;
import com.brilliantsofts.EliteUniversity.entity.Orientation;

public class OrientationMapper {
    public static Orientation toEntity(OrientationRequest request) {
        Orientation entity = new Orientation();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setScheduledDate(request.getScheduledDate());
        entity.setVenue(request.getVenue());
        entity.setSemesterId(request.getSemesterId());
        entity.setAcademicSessionId(request.getAcademicSessionId());
        entity.setStatus(request.getStatus());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return entity;
    }

    public static OrientationResponse toResponse(Orientation entity) {
        OrientationResponse response = new OrientationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setScheduledDate(entity.getScheduledDate());
        response.setVenue(entity.getVenue());
        response.setSemesterId(entity.getSemesterId());
        response.setAcademicSessionId(entity.getAcademicSessionId());
        response.setStatus(entity.getStatus());
        response.setIsActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
