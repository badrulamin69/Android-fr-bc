package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.RoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoleResponse;
import com.brilliantsofts.EliteUniversity.entity.Role;

import java.util.ArrayList;
import java.util.UUID;

public class RoleMapper {
    public static Role toEntity(RoleRequest request) {
        Role entity = new Role();
        entity.setUniqueCode(UUID.randomUUID().toString());
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
        return entity;
    }

    public static RoleResponse toResponse(Role entity) {
        RoleResponse response = new RoleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getPermissions() != null) {
            response.setPermissions(entity.getPermissions().stream()
                    .map(PermissionMapper::toResponse)
                    .toList());
        } else {
            response.setPermissions(new ArrayList<>());
        }
        return response;
    }
}
