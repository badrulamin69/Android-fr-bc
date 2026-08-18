package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.UserPermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserPermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPermissionService {
    UserPermissionResponse create(UserPermissionRequest request);
    UserPermissionResponse update(Long id, UserPermissionRequest request);
    UserPermissionResponse getById(Long id);
    Page<UserPermissionResponse> getAll(Pageable pageable);
    List<UserPermissionResponse> getByUserId(Long userId);
    void delete(Long id);
}
