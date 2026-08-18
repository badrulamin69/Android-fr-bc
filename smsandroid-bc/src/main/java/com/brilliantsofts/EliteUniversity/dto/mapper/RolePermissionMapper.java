package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.RolePermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RolePermissionResponse;
import com.brilliantsofts.EliteUniversity.entity.RolePermission;

import java.util.UUID;

public class RolePermissionMapper {
    public static RolePermission toEntity(RolePermissionRequest request) {
        RolePermission entity = new RolePermission();
        entity.setUniqueCode(UUID.randomUUID().toString());
        return entity;
    }

    public static RolePermissionResponse toResponse(RolePermission entity) {
        RolePermissionResponse response = new RolePermissionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getRole() != null) {
            response.setRoleId(entity.getRole().getId());
            response.setRoleName(entity.getRole().getName());
        }
        if (entity.getPermission() != null) {
            response.setPermissionId(entity.getPermission().getId());
            response.setPermissionName(entity.getPermission().getName());
        }
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
