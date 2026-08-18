package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.HostelAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelAllocationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HostelAllocationService {
    HostelAllocationResponse create(HostelAllocationRequest request);
    HostelAllocationResponse update(Long id, HostelAllocationRequest request);
    HostelAllocationResponse getById(Long id);
    Page<HostelAllocationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
