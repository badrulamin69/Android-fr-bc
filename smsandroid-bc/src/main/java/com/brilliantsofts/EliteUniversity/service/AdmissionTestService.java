package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmissionTestService {
    AdmissionTestResponse create(AdmissionTestRequest request);
    AdmissionTestResponse update(Long id, AdmissionTestRequest request);
    AdmissionTestResponse getById(Long id);
    Page<AdmissionTestResponse> getAll(Pageable pageable);
    void delete(Long id);
    AdmissionTestResponse publish(Long id);
    AdmissionTestResponse close(Long id);
    Map<String, Object> getStats();
}
