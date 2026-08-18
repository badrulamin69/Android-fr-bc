package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.UserRoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserRoleResponse;
import com.brilliantsofts.EliteUniversity.entity.UserRole;

import java.util.UUID;

public class UserRoleMapper {
    public static UserRole toEntity(UserRoleRequest request) {
        UserRole entity = new UserRole();
        entity.setUniqueCode(UUID.randomUUID().toString());
        return entity;
    }

    public static UserRoleResponse toResponse(UserRole entity) {
        UserRoleResponse response = new UserRoleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
            response.setUsername(entity.getUser().getUsername());
        }
        if (entity.getRole() != null) {
            response.setRoleId(entity.getRole().getId());
            response.setRoleName(entity.getRole().getName());
        }
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
