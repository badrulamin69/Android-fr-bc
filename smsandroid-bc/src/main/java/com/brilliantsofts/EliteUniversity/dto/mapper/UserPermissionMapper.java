package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.UserPermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserPermissionResponse;
import com.brilliantsofts.EliteUniversity.entity.UserPermission;

import java.util.UUID;

public class UserPermissionMapper {
    public static UserPermission toEntity(UserPermissionRequest request) {
        UserPermission entity = new UserPermission();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setGranted(request.isGranted());
        entity.setNotes(request.getNotes());
        entity.setOverriddenById(request.getOverriddenById());
        entity.setExpiresAt(request.getExpiresAt());
        return entity;
    }

    public static UserPermissionResponse toResponse(UserPermission entity) {
        UserPermissionResponse response = new UserPermissionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
            response.setUsername(entity.getUser().getUsername());
        }
        if (entity.getPermission() != null) {
            response.setPermissionId(entity.getPermission().getId());
            response.setPermissionName(entity.getPermission().getName());
        }
        response.setGranted(entity.isGranted());
        response.setNotes(entity.getNotes());
        response.setOverriddenById(entity.getOverriddenById());
        response.setExpiresAt(entity.getExpiresAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
