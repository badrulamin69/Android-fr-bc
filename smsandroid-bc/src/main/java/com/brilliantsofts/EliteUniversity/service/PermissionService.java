package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.PermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    PermissionResponse update(Long id, PermissionRequest request);
    PermissionResponse getById(Long id);
    Page<PermissionResponse> getAll(Pageable pageable);
    List<PermissionResponse> getByModule(String module);
    void delete(Long id);
}
