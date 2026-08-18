package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.OrientationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OrientationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrientationService {
    OrientationResponse create(OrientationRequest request);
    OrientationResponse update(Long id, OrientationRequest request);
    OrientationResponse getById(Long id);
    Page<OrientationResponse> getAll(String search, String status, Pageable pageable);
    void delete(Long id);
}
