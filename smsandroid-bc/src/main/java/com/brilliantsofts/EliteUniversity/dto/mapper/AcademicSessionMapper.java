package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicSessionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicSessionResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;

public class AcademicSessionMapper {
    public static AcademicSession toEntity(AcademicSessionRequest request) {
        AcademicSession entity = new AcademicSession();
        entity.setSessionName(request.getSessionName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActive(request.isActive());
        return entity;
    }

    public static AcademicSessionResponse toResponse(AcademicSession entity) {
        AcademicSessionResponse response = new AcademicSessionResponse();
        response.setId(entity.getId());
        response.setSessionName(entity.getSessionName());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setActive(entity.isActive());
        return response;
    }
}
