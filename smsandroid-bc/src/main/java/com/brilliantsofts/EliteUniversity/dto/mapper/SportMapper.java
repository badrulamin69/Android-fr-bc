package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SportResponse;
import com.brilliantsofts.EliteUniversity.entity.Sport;

public class SportMapper {
    public static Sport toEntity(SportRequest request) {
        Sport entity = new Sport();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setCoachName(request.getCoachName());
        entity.setMaxParticipants(request.getMaxParticipants());
        entity.setActive(request.isActive());
        return entity;
    }

    public static SportResponse toResponse(Sport entity) {
        SportResponse response = new SportResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setCoachName(entity.getCoachName());
        response.setMaxParticipants(entity.getMaxParticipants());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
