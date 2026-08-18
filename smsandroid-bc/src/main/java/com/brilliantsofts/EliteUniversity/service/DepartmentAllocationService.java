package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.DepartmentAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentAllocationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentAllocationService {
    DepartmentAllocationResponse create(DepartmentAllocationRequest request);
    DepartmentAllocationResponse update(Long id, DepartmentAllocationRequest request);
    DepartmentAllocationResponse confirm(Long id);
    DepartmentAllocationResponse cancel(Long id);
    DepartmentAllocationResponse getById(Long id);
    Page<DepartmentAllocationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}