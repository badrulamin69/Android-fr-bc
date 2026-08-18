package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicPolicyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicPolicyResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicPolicy;

public class AcademicPolicyMapper {
    public static AcademicPolicy toEntity(AcademicPolicyRequest request) {
        AcademicPolicy entity = new AcademicPolicy();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPolicyType(request.getPolicyType());
        entity.setPolicyValue(request.getPolicyValue());
        entity.setProgramId(request.getProgramId());
        entity.setActive(request.isActive());
        entity.setEffectiveFrom(request.getEffectiveFrom());
        entity.setEffectiveTo(request.getEffectiveTo());
        return entity;
    }

    public static AcademicPolicyResponse toResponse(AcademicPolicy entity) {
        AcademicPolicyResponse response = new AcademicPolicyResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setPolicyType(entity.getPolicyType());
        response.setPolicyValue(entity.getPolicyValue());
        response.setProgramId(entity.getProgramId());
        response.setActive(entity.isActive());
        response.setEffectiveFrom(entity.getEffectiveFrom());
        response.setEffectiveTo(entity.getEffectiveTo());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
