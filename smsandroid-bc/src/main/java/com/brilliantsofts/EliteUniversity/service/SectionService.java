package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SectionService {
    SectionResponse create(SectionRequest request);
    SectionResponse update(Long id, SectionRequest request);
    SectionResponse getById(Long id);
    Page<SectionResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
