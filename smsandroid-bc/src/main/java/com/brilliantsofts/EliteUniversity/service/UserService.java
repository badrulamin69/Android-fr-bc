package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.UserRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse update(Long id, UserRequest request);
    UserResponse getById(Long id);
    UserResponse getByUsername(String username);
    UserResponse getByEmail(String email);
    UserResponse getByUsernameOrEmail(String value);
    org.springframework.data.domain.Page<UserResponse> getAll(org.springframework.data.domain.Pageable pageable);
    void delete(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
