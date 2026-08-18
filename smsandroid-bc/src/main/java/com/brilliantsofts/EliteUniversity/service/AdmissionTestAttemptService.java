package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestAttemptResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionTestAttemptService {
    AdmissionTestAttemptResponse getById(Long id);
    Page<AdmissionTestAttemptResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
