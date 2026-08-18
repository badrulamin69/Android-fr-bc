package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EligibilityVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityVerificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface EligibilityVerificationService {
    EligibilityVerificationResponse verify(EligibilityVerificationRequest request);
    List<EligibilityVerificationResponse> autoVerifyAll(Long testId);
    EligibilityVerificationResponse getById(Long id);
    Page<EligibilityVerificationResponse> getAll(String search, Long testId, Pageable pageable);
    List<EligibilityVerificationResponse> getByTestId(Long testId);
    Map<String, Object> getStats(Long testId);
    void delete(Long id);
}