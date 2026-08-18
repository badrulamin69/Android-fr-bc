package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.FacultyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FacultyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacultyService {
    FacultyResponse create(FacultyRequest request);
    FacultyResponse update(Long id, FacultyRequest request);
    FacultyResponse getById(Long id);
    Page<FacultyResponse> getAll(Pageable pageable);
    void delete(Long id);
}
