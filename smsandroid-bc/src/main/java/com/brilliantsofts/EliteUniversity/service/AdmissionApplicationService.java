package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionApplicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmissionApplicationService {
    AdmissionApplicationResponse create(AdmissionApplicationRequest request);
    AdmissionApplicationResponse update(Long id, AdmissionApplicationRequest request);
    AdmissionApplicationResponse getById(Long id);
    Page<AdmissionApplicationResponse> getAll(Pageable pageable);
    void delete(Long id);
    Page<AdmissionApplicationResponse> getUnverified(Pageable pageable);
    Map<String, Object> getStats();
}
