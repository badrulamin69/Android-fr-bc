package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EligibilityCriteriaRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityCriteriaResponse;
import com.brilliantsofts.EliteUniversity.entity.EligibilityCriteria;

public class EligibilityCriteriaMapper {
    public static EligibilityCriteria toEntity(EligibilityCriteriaRequest request) {
        EligibilityCriteria entity = new EligibilityCriteria();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setCriteriaType(request.getCriteriaType());
        entity.setMinValue(request.getMinValue());
        entity.setMaxValue(request.getMaxValue());
        entity.setApplicableTo(request.getApplicableTo());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setFacultyId(request.getFacultyId());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static EligibilityCriteriaResponse toResponse(EligibilityCriteria entity) {
        EligibilityCriteriaResponse response = new EligibilityCriteriaResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setCriteriaType(entity.getCriteriaType());
        response.setMinValue(entity.getMinValue());
        response.setMaxValue(entity.getMaxValue());
        response.setApplicableTo(entity.getApplicableTo());
        response.setProgramId(entity.getProgramId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setFacultyId(entity.getFacultyId());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}