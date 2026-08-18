package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.UniversityRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UniversityResponse;
import com.brilliantsofts.EliteUniversity.entity.University;

public class UniversityMapper {
    public static University toEntity(UniversityRequest request) {
        University entity = new University();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setWebsite(request.getWebsite());
        entity.setLogoUrl(request.getLogoUrl());
        entity.setEstablishedYear(request.getEstablishedYear());
        entity.setMotto(request.getMotto());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static UniversityResponse toResponse(University entity) {
        UniversityResponse response = new UniversityResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());
        response.setEmail(entity.getEmail());
        response.setWebsite(entity.getWebsite());
        response.setLogoUrl(entity.getLogoUrl());
        response.setEstablishedYear(entity.getEstablishedYear());
        response.setMotto(entity.getMotto());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
