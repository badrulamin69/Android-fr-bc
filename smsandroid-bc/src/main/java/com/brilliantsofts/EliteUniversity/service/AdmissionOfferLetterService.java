package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionOfferLetterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionOfferLetterResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface AdmissionOfferLetterService {
    AdmissionOfferLetterResponse create(AdmissionOfferLetterRequest request);
    AdmissionOfferLetterResponse update(Long id, AdmissionOfferLetterRequest request);
    AdmissionOfferLetterResponse getById(Long id);
    Page<AdmissionOfferLetterResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    AdmissionOfferLetterResponse accept(Long id);
    AdmissionOfferLetterResponse decline(Long id, String reason);
    Map<String, Object> getStats();
}
