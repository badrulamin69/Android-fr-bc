package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TransportAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportAllocationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransportAllocationService {
    TransportAllocationResponse create(TransportAllocationRequest request);
    TransportAllocationResponse update(Long id, TransportAllocationRequest request);
    TransportAllocationResponse getById(Long id);
    Page<TransportAllocationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
