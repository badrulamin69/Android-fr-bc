package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AlumniRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AlumniResponse;
import com.brilliantsofts.EliteUniversity.entity.Alumni;

import java.util.UUID;

public class AlumniMapper {
    public static Alumni toEntity(AlumniRequest request) {
        Alumni entity = new Alumni();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setGraduationDate(request.getGraduationDate());
        entity.setDegree(request.getDegree());
        entity.setCurrentCompany(request.getCurrentCompany());
        entity.setCurrentDesignation(request.getCurrentDesignation());
        entity.setCurrentLocation(request.getCurrentLocation());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setLinkedInProfile(request.getLinkedInProfile());
        entity.setIsAvailableForMentoring(request.getIsAvailableForMentoring());
        entity.setIsAvailableForRecruitment(request.getIsAvailableForRecruitment());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AlumniResponse toResponse(Alumni entity) {
        AlumniResponse response = new AlumniResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setGraduationDate(entity.getGraduationDate());
        response.setDegree(entity.getDegree());
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
        }
        if (entity.getDepartment() != null) {
            response.setDepartmentId(entity.getDepartment().getId());
        }
        response.setCurrentCompany(entity.getCurrentCompany());
        response.setCurrentDesignation(entity.getCurrentDesignation());
        response.setCurrentLocation(entity.getCurrentLocation());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setLinkedInProfile(entity.getLinkedInProfile());
        response.setIsAvailableForMentoring(entity.getIsAvailableForMentoring());
        response.setIsAvailableForRecruitment(entity.getIsAvailableForRecruitment());
        response.setRemarks(entity.getRemarks());
        return response;
    }
}
