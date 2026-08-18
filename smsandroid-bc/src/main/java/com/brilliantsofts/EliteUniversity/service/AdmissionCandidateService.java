package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCandidateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCandidateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionCandidateService {
    AdmissionCandidateResponse create(AdmissionCandidateRequest request);
    AdmissionCandidateResponse update(Long id, AdmissionCandidateRequest request);
    AdmissionCandidateResponse getById(Long id);
    Page<AdmissionCandidateResponse> getAll(Pageable pageable);
    void delete(Long id);
}
