package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdministrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationResponse;
import com.brilliantsofts.EliteUniversity.entity.Administration;

public class AdministrationMapper {
    public static Administration toEntity(AdministrationRequest request) {
        Administration entity = new Administration();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setEmployeeCode(request.getEmployeeCode());
        entity.setQualification(request.getQualification());
        entity.setSpecialization(request.getSpecialization());
        entity.setJoiningDate(request.getJoiningDate());
        entity.setStatus(request.getStatus());
        entity.setUserId(request.getUserId());
        entity.setDepartmentId(request.getDepartmentId());
        return entity;
    }

    public static AdministrationResponse toResponse(Administration entity) {
        AdministrationResponse response = new AdministrationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setEmployeeCode(entity.getEmployeeCode());
        response.setQualification(entity.getQualification());
        response.setSpecialization(entity.getSpecialization());
        response.setJoiningDate(entity.getJoiningDate());
        response.setStatus(entity.getStatus());
        response.setUserId(entity.getUserId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
