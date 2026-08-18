package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.DepartmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse update(Long id, DepartmentRequest request);
    DepartmentResponse getById(Long id);
    Page<DepartmentResponse> getAll(Pageable pageable);
    List<DepartmentResponse> getByFaculty(Long facultyId);
    void delete(Long id);
}
