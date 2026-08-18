package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.RolePermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RolePermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolePermissionService {
    RolePermissionResponse create(RolePermissionRequest request);
    RolePermissionResponse update(Long id, RolePermissionRequest request);
    RolePermissionResponse getById(Long id);
    Page<RolePermissionResponse> getAll(Pageable pageable);
    void delete(Long id);
}
