package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CurriculumRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CurriculumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurriculumService {
    CurriculumResponse create(CurriculumRequest request);
    CurriculumResponse update(Long id, CurriculumRequest request);
    CurriculumResponse getById(Long id);
    Page<CurriculumResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
