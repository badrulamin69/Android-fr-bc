package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicationReviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicationReviewResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ApplicationReviewService {
    ApplicationReviewResponse create(ApplicationReviewRequest request);
    ApplicationReviewResponse update(Long id, ApplicationReviewRequest request);
    ApplicationReviewResponse getById(Long id);
    Page<ApplicationReviewResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
