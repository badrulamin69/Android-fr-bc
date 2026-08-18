package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SectionResponse;
import com.brilliantsofts.EliteUniversity.entity.Section;

public class SectionMapper {
    public static Section toEntity(SectionRequest request) {
        Section entity = new Section();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setBatchId(request.getBatchId());
        return entity;
    }

    public static SectionResponse toResponse(Section entity) {
        SectionResponse response = new SectionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setBatchId(entity.getBatchId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
