package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.GuardianRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GuardianResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface GuardianService {
    GuardianResponse create(GuardianRequest request);
    GuardianResponse update(Long id, GuardianRequest request);
    GuardianResponse getById(Long id);
    Page<GuardianResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
