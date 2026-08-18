package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.UniversityRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UniversityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UniversityService {
    UniversityResponse create(UniversityRequest request);
    UniversityResponse update(Long id, UniversityRequest request);
    UniversityResponse getById(Long id);
    Page<UniversityResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
