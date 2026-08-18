package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.PrerequisiteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PrerequisiteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrerequisiteService {
    PrerequisiteResponse create(PrerequisiteRequest request);
    PrerequisiteResponse update(Long id, PrerequisiteRequest request);
    PrerequisiteResponse getById(Long id);
    Page<PrerequisiteResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
