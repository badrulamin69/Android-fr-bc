package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentRequest request);
    EnrollmentResponse update(Long id, EnrollmentRequest request);
    EnrollmentResponse getById(Long id);
    Page<EnrollmentResponse> getAll(Pageable pageable);
    List<EnrollmentResponse> getByStudent(Long studentId);
    List<EnrollmentResponse> getByCourse(Long courseId);
    List<EnrollmentResponse> getBySemester(Long studentId, String semester);
    void delete(Long id);
}
