package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramService {
    ProgramResponse create(ProgramRequest request);
    ProgramResponse update(Long id, ProgramRequest request);
    ProgramResponse getById(Long id);
    Page<ProgramResponse> getAll(Pageable pageable);
    List<ProgramResponse> getByDepartment(Long departmentId);
    void delete(Long id);
}
