package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.GuardianRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GuardianResponse;
import com.brilliantsofts.EliteUniversity.entity.Guardian;

import java.util.UUID;

public class GuardianMapper {
    public static Guardian toEntity(GuardianRequest request) {
        Guardian entity = new Guardian();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setRelationship(request.getRelationship());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setOccupation(request.getOccupation());
        entity.setAddress(request.getAddress());
        entity.setIsPrimary(request.getIsPrimary());
        return entity;
    }

    public static GuardianResponse toResponse(Guardian entity) {
        GuardianResponse response = new GuardianResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setRelationship(entity.getRelationship());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setOccupation(entity.getOccupation());
        response.setAddress(entity.getAddress());
        response.setIsPrimary(entity.getIsPrimary());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
