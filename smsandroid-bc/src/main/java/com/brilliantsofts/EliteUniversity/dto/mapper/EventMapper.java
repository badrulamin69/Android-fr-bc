package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EventRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventResponse;
import com.brilliantsofts.EliteUniversity.entity.Event;

import java.util.UUID;

public class EventMapper {
    public static Event toEntity(EventRequest request) {
        Event entity = new Event();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEventType(request.getEventType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setVenue(request.getVenue());
        entity.setClubId(request.getClubId());
        entity.setMaxParticipants(request.getMaxParticipants());
        entity.setRegistrationFee(request.getRegistrationFee());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static EventResponse toResponse(Event entity) {
        EventResponse response = new EventResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setEventType(entity.getEventType());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setVenue(entity.getVenue());
        response.setClubId(entity.getClubId());
        response.setMaxParticipants(entity.getMaxParticipants());
        response.setRegistrationFee(entity.getRegistrationFee());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
