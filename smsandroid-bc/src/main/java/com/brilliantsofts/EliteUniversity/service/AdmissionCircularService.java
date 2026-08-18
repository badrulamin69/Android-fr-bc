package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCircularRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCircularResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionCircularService {
    AdmissionCircularResponse create(AdmissionCircularRequest request);
    AdmissionCircularResponse update(Long id, AdmissionCircularRequest request);
    AdmissionCircularResponse getById(Long id);
    Page<AdmissionCircularResponse> getAll(Pageable pageable);
    void delete(Long id);
}
