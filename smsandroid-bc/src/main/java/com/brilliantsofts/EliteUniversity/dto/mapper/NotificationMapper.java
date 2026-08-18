package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.NotificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NotificationResponse;
import com.brilliantsofts.EliteUniversity.entity.Notification;

import java.util.UUID;

public class NotificationMapper {
    public static Notification toEntity(NotificationRequest request) {
        Notification entity = new Notification();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setTitle(request.getTitle());
        entity.setMessage(request.getMessage());
        entity.setType(request.getType());
        entity.setRead(Boolean.TRUE.equals(request.getIsRead()));
        entity.setReferenceType(request.getReferenceType());
        entity.setReferenceId(request.getReferenceId());
        return entity;
    }

    public static NotificationResponse toResponse(Notification entity) {
        NotificationResponse response = new NotificationResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
        }
        response.setTitle(entity.getTitle());
        response.setMessage(entity.getMessage());
        response.setType(entity.getType());
        response.setRead(entity.isRead());
        response.setReferenceType(entity.getReferenceType());
        response.setReferenceId(entity.getReferenceId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
