package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRegistrationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface SemesterRegistrationService {
    SemesterRegistrationResponse create(SemesterRegistrationRequest request);
    SemesterRegistrationResponse update(Long id, SemesterRegistrationRequest request);
    SemesterRegistrationResponse getById(Long id);
    Page<SemesterRegistrationResponse> getAll(Pageable pageable, String search);
    Map<String, Object> getStats();
    void delete(Long id);
}
