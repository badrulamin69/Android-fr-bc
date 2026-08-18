package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EventRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.EventRegistration;

import java.util.UUID;

public class EventRegistrationMapper {
    public static EventRegistration toEntity(EventRegistrationRequest request) {
        EventRegistration entity = new EventRegistration();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setRegistrationDate(request.getRegistrationDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static EventRegistrationResponse toResponse(EventRegistration entity) {
        EventRegistrationResponse response = new EventRegistrationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getEvent() != null) {
            response.setEventId(entity.getEvent().getId());
            response.setEventTitle(entity.getEvent().getTitle());
        }
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
        }
        response.setRegistrationDate(entity.getRegistrationDate());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
