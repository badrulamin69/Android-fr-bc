package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ReportTemplateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ReportTemplateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportTemplateService {
    ReportTemplateResponse create(ReportTemplateRequest request);
    ReportTemplateResponse update(Long id, ReportTemplateRequest request);
    ReportTemplateResponse getById(Long id);
    Page<ReportTemplateResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
