package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentFeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentFeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentFeeService {
    StudentFeeResponse create(StudentFeeRequest request);
    StudentFeeResponse update(Long id, StudentFeeRequest request);
    StudentFeeResponse getById(Long id);
    Page<StudentFeeResponse> getAll(Pageable pageable);
    void delete(Long id);
}
