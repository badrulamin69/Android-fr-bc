package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.FeeTypeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeTypeResponse;
import com.brilliantsofts.EliteUniversity.entity.FeeType;

public class FeeTypeMapper {
    public static FeeType toEntity(FeeTypeRequest request) {
        FeeType entity = new FeeType();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setDefaultAmount(request.getDefaultAmount());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static FeeTypeResponse toResponse(FeeType entity) {
        FeeTypeResponse response = new FeeTypeResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setCategory(entity.getCategory());
        response.setDescription(entity.getDescription());
        response.setDefaultAmount(entity.getDefaultAmount());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
