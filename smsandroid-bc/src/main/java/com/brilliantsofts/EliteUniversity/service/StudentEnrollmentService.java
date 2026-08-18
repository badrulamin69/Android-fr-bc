package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentEnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentEnrollmentService {
    StudentEnrollmentResponse create(StudentEnrollmentRequest request);
    StudentEnrollmentResponse update(Long id, StudentEnrollmentRequest request);
    StudentEnrollmentResponse getById(Long id);
    Page<StudentEnrollmentResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
