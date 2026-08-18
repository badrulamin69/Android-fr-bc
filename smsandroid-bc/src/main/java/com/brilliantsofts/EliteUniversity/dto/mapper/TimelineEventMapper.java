package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TimelineEventRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TimelineEventResponse;
import com.brilliantsofts.EliteUniversity.entity.TimelineEvent;

public class TimelineEventMapper {
    public static TimelineEvent toEntity(TimelineEventRequest request) {
        TimelineEvent entity = new TimelineEvent();
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setUserId(request.getUserId());
        entity.setEventType(request.getEventType());
        entity.setDescription(request.getDescription());
        entity.setOldValue(request.getOldValue());
        entity.setNewValue(request.getNewValue());
        entity.setIpAddress(request.getIpAddress());
        entity.setSeverity(request.getSeverity());
        return entity;
    }

    public static TimelineEventResponse toResponse(TimelineEvent entity) {
        TimelineEventResponse response = new TimelineEventResponse();
        response.setId(entity.getId());
        response.setEntityType(entity.getEntityType());
        response.setEntityId(entity.getEntityId());
        response.setUserId(entity.getUserId());
        response.setEventType(entity.getEventType());
        response.setDescription(entity.getDescription());
        response.setOldValue(entity.getOldValue());
        response.setNewValue(entity.getNewValue());
        response.setIpAddress(entity.getIpAddress());
        response.setSeverity(entity.getSeverity());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
