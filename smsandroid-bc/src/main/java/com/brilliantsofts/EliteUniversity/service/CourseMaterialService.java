package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseMaterialRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseMaterialResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseMaterialService {
    CourseMaterialResponse create(CourseMaterialRequest request);
    CourseMaterialResponse update(Long id, CourseMaterialRequest request);
    CourseMaterialResponse getById(Long id);
    Page<CourseMaterialResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
