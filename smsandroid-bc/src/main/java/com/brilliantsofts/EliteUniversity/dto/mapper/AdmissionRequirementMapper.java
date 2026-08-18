package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionRequirementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionRequirementResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionRequirement;

public class AdmissionRequirementMapper {
    public static AdmissionRequirement toEntity(AdmissionRequirementRequest request) {
        AdmissionRequirement entity = new AdmissionRequirement();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setRequirementType(request.getRequirementType());
        entity.setApplicableTo(request.getApplicableTo());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setFacultyId(request.getFacultyId());
        entity.setMandatory(request.isMandatory());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static AdmissionRequirementResponse toResponse(AdmissionRequirement entity) {
        AdmissionRequirementResponse response = new AdmissionRequirementResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setRequirementType(entity.getRequirementType());
        response.setApplicableTo(entity.getApplicableTo());
        response.setProgramId(entity.getProgramId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setFacultyId(entity.getFacultyId());
        response.setMandatory(entity.isMandatory());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
