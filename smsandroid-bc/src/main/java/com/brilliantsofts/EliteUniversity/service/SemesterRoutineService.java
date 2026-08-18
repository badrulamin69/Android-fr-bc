package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRoutineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SemesterRoutineService {
    SemesterRoutineResponse create(SemesterRoutineRequest request);
    SemesterRoutineResponse update(Long id, SemesterRoutineRequest request);
    SemesterRoutineResponse getById(Long id);
    Page<SemesterRoutineResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
