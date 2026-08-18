package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AuditLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AuditLogResponse;
import com.brilliantsofts.EliteUniversity.entity.AuditLog;

import java.util.UUID;

public class AuditLogMapper {
    public static AuditLog toEntity(AuditLogRequest request) {
        AuditLog entity = new AuditLog();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setAction(request.getAction());
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setOldValue(request.getOldValue());
        entity.setNewValue(request.getNewValue());
        entity.setIpAddress(request.getIpAddress());
        return entity;
    }

    public static AuditLogResponse toResponse(AuditLog entity) {
        AuditLogResponse response = new AuditLogResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setAction(entity.getAction());
        response.setEntityType(entity.getEntityType());
        response.setEntityId(entity.getEntityId());
        response.setOldValue(entity.getOldValue());
        response.setNewValue(entity.getNewValue());
        response.setIpAddress(entity.getIpAddress());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getUser() != null) {
            AuditLogResponse.AuditLogUserResponse userSummary = new AuditLogResponse.AuditLogUserResponse();
            userSummary.setId(entity.getUser().getId());
            userSummary.setUsername(entity.getUser().getUsername());
            response.setUser(userSummary);
        }
        return response;
    }
}
