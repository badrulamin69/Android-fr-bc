package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.UserRoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserRoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRoleService {
    UserRoleResponse create(UserRoleRequest request);
    UserRoleResponse update(Long id, UserRoleRequest request);
    UserRoleResponse getById(Long id);
    Page<UserRoleResponse> getAll(Pageable pageable);
    void delete(Long id);
}
