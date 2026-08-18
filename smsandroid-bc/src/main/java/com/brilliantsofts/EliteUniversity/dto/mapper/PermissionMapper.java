package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.PermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PermissionResponse;
import com.brilliantsofts.EliteUniversity.entity.Permission;

import java.util.UUID;

public class PermissionMapper {
    public static Permission toEntity(PermissionRequest request) {
        Permission entity = new Permission();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setModule(request.getModule());
        entity.setAction(request.getAction());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static PermissionResponse toResponse(Permission entity) {
        PermissionResponse response = new PermissionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setModule(entity.getModule());
        response.setAction(entity.getAction());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
