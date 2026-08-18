package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseAssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseAssignmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseAssignmentService {
    CourseAssignmentResponse create(CourseAssignmentRequest request);
    CourseAssignmentResponse update(Long id, CourseAssignmentRequest request);
    CourseAssignmentResponse getById(Long id);
    Page<CourseAssignmentResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}