package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ActivityLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ActivityLogResponse;
import com.brilliantsofts.EliteUniversity.entity.ActivityLog;

import java.util.UUID;

public class ActivityLogMapper {
    public static ActivityLog toEntity(ActivityLogRequest request) {
        ActivityLog entity = new ActivityLog();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setUserId(request.getUserId());
        entity.setUsername(request.getUsername());
        entity.setAction(request.getAction());
        entity.setModule(request.getModule());
        entity.setDescription(request.getDescription());
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setIpAddress(request.getIpAddress());
        entity.setUserAgent(request.getUserAgent());
        entity.setMetadata(request.getMetadata());
        return entity;
    }

    public static ActivityLogResponse toResponse(ActivityLog entity) {
        ActivityLogResponse response = new ActivityLogResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setUserId(entity.getUserId());
        response.setUsername(entity.getUsername());
        response.setAction(entity.getAction());
        response.setModule(entity.getModule());
        response.setDescription(entity.getDescription());
        response.setEntityType(entity.getEntityType());
        response.setEntityId(entity.getEntityId());
        response.setIpAddress(entity.getIpAddress());
        response.setUserAgent(entity.getUserAgent());
        response.setMetadata(entity.getMetadata());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
