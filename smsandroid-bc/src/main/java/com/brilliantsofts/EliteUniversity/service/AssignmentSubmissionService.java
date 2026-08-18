package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AssignmentSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentSubmissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssignmentSubmissionService {
    AssignmentSubmissionResponse create(AssignmentSubmissionRequest request);
    AssignmentSubmissionResponse update(Long id, AssignmentSubmissionRequest request);
    AssignmentSubmissionResponse getById(Long id);
    Page<AssignmentSubmissionResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
