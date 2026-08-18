package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SubjectRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SubjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectManagementService {
    SubjectResponse create(SubjectRequest request);
    SubjectResponse update(Long id, SubjectRequest request);
    SubjectResponse getById(Long id);
    Page<SubjectResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
