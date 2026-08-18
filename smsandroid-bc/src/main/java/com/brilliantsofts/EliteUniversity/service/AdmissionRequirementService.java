package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionRequirementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionRequirementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionRequirementService {
    AdmissionRequirementResponse create(AdmissionRequirementRequest request);
    AdmissionRequirementResponse update(Long id, AdmissionRequirementRequest request);
    AdmissionRequirementResponse getById(Long id);
    Page<AdmissionRequirementResponse> getAll(String search, String status, Pageable pageable);
    void delete(Long id);
}
