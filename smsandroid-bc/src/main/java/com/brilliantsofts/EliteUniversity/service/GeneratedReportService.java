package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.GeneratedReportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GeneratedReportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneratedReportService {
    GeneratedReportResponse create(GeneratedReportRequest request);
    GeneratedReportResponse update(Long id, GeneratedReportRequest request);
    GeneratedReportResponse getById(Long id);
    Page<GeneratedReportResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}