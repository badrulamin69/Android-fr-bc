package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionEnrollmentResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface AdmissionEnrollmentService {
    AdmissionEnrollmentResponse create(AdmissionEnrollmentRequest request);
    AdmissionEnrollmentResponse update(Long id, AdmissionEnrollmentRequest request);
    AdmissionEnrollmentResponse getById(Long id);
    Page<AdmissionEnrollmentResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
