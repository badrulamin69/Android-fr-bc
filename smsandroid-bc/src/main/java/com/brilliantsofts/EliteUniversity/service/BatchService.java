package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BatchRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BatchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BatchService {
    BatchResponse create(BatchRequest request);
    BatchResponse update(Long id, BatchRequest request);
    BatchResponse getById(Long id);
    Page<BatchResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
