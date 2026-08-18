package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdministrationDivisionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationDivisionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdministrationDivision;

public class AdministrationDivisionMapper {
    public static AdministrationDivision toEntity(AdministrationDivisionRequest request) {
        AdministrationDivision entity = new AdministrationDivision();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setDeanName(request.getDeanName());
        entity.setCampusId(request.getCampusId());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return entity;
    }

    public static AdministrationDivisionResponse toResponse(AdministrationDivision entity) {
        AdministrationDivisionResponse response = new AdministrationDivisionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setDeanName(entity.getDeanName());
        response.setCampusId(entity.getCampusId());
        response.setIsActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
