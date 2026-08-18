package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ExamCenterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamCenterResponse;
import com.brilliantsofts.EliteUniversity.entity.ExamCenter;

public class ExamCenterMapper {
    public static ExamCenter toEntity(ExamCenterRequest request) {
        ExamCenter entity = new ExamCenter();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setTotalCapacity(request.getTotalCapacity());
        entity.setContactPerson(request.getContactPerson());
        entity.setContactPhone(request.getContactPhone());
        entity.setActive(request.isActive());
        return entity;
    }

    public static ExamCenterResponse toResponse(ExamCenter entity) {
        ExamCenterResponse response = new ExamCenterResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setAddress(entity.getAddress());
        response.setCity(entity.getCity());
        response.setTotalCapacity(entity.getTotalCapacity());
        response.setContactPerson(entity.getContactPerson());
        response.setContactPhone(entity.getContactPhone());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
