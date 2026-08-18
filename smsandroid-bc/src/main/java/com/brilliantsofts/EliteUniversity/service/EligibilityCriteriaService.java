package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EligibilityCriteriaRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityCriteriaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EligibilityCriteriaService {
    EligibilityCriteriaResponse create(EligibilityCriteriaRequest request);
    EligibilityCriteriaResponse update(Long id, EligibilityCriteriaRequest request);
    EligibilityCriteriaResponse getById(Long id);
    Page<EligibilityCriteriaResponse> getAll(String search, String status, Pageable pageable);
    void delete(Long id);
}