package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SemesterService {
    SemesterResponse create(SemesterRequest request);
    SemesterResponse update(Long id, SemesterRequest request);
    SemesterResponse getById(Long id);
    Page<SemesterResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
