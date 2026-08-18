package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicPolicyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicPolicyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademicPolicyService {
    AcademicPolicyResponse create(AcademicPolicyRequest request);
    AcademicPolicyResponse update(Long id, AcademicPolicyRequest request);
    AcademicPolicyResponse getById(Long id);
    Page<AcademicPolicyResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
