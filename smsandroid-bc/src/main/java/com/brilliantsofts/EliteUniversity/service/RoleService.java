package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.RoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    RoleResponse update(Long id, RoleRequest request);
    RoleResponse getById(Long id);
    Page<RoleResponse> getAll(Pageable pageable);
    void delete(Long id);
}
