package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.UserRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserResponse;
import com.brilliantsofts.EliteUniversity.entity.User;

public class UserMapper {
    public static User toEntity(UserRequest request) {
        User entity = new User();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setPhone(request.getPhone());
        entity.setEnabled(request.isEnabled());
        entity.setAccountNonLocked(request.isAccountNonLocked());
        entity.setAccountNonExpired(request.isAccountNonExpired());
        entity.setCredentialsNonExpired(request.isCredentialsNonExpired());
        entity.setRole(request.getRole());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setEnabled(entity.isEnabled());
        response.setAccountNonLocked(entity.isAccountNonLocked());
        response.setAccountNonExpired(entity.isAccountNonExpired());
        response.setCredentialsNonExpired(entity.isCredentialsNonExpired());
        response.setRole(entity.getRole());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
