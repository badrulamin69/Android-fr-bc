package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssignmentService {
    AssignmentResponse create(AssignmentRequest request);
    AssignmentResponse update(Long id, AssignmentRequest request);
    AssignmentResponse getById(Long id);
    Page<AssignmentResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
