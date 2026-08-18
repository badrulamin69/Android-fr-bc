package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterResponse;
import com.brilliantsofts.EliteUniversity.entity.Semester;

public class SemesterMapper {
    public static Semester toEntity(SemesterRequest request) {
        Semester entity = new Semester();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAcademicSessionId(request.getAcademicSessionId());
        entity.setOrderNo(request.getOrderNo());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setRegistrationDeadline(request.getRegistrationDeadline());
        entity.setStatus(request.getStatus());
        entity.setActive(request.isActive());
        return entity;
    }

    public static SemesterResponse toResponse(Semester entity) {
        SemesterResponse response = new SemesterResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setAcademicSessionId(entity.getAcademicSessionId());
        response.setOrderNo(entity.getOrderNo());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setRegistrationDeadline(entity.getRegistrationDeadline());
        response.setStatus(entity.getStatus());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
