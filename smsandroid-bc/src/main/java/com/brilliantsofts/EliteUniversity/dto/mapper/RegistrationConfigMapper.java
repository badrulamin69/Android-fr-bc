package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.RegistrationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RegistrationConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.RegistrationConfig;

public class RegistrationConfigMapper {
    public static RegistrationConfig toEntity(RegistrationConfigRequest request) {
        RegistrationConfig entity = new RegistrationConfig();
        entity.setSemesterId(request.getSemesterId());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        entity.setAllowAddDrop(request.isAllowAddDrop());
        entity.setAddDropDeadline(request.getAddDropDeadline());
        entity.setAdvisorApprovalRequired(request.isAdvisorApprovalRequired());
        entity.setPaymentRequired(request.isPaymentRequired());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static RegistrationConfigResponse toResponse(RegistrationConfig entity) {
        RegistrationConfigResponse response = new RegistrationConfigResponse();
        response.setId(entity.getId());
        response.setSemesterId(entity.getSemesterId());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setMinCredits(entity.getMinCredits());
        response.setMaxCredits(entity.getMaxCredits());
        response.setAllowAddDrop(entity.isAllowAddDrop());
        response.setAddDropDeadline(entity.getAddDropDeadline());
        response.setAdvisorApprovalRequired(entity.isAdvisorApprovalRequired());
        response.setPaymentRequired(entity.isPaymentRequired());
        response.setActive(entity.isActive());
        response.setClosed(entity.isClosed());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
