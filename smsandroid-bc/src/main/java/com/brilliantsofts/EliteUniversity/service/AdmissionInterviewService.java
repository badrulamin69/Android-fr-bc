package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionInterviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionInterviewResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface AdmissionInterviewService {
    AdmissionInterviewResponse create(AdmissionInterviewRequest request);
    AdmissionInterviewResponse update(Long id, AdmissionInterviewRequest request);
    AdmissionInterviewResponse getById(Long id);
    Page<AdmissionInterviewResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
