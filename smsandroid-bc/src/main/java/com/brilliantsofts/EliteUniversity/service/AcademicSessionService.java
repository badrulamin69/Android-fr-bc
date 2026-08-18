package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicSessionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicSessionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademicSessionService {
    AcademicSessionResponse create(AcademicSessionRequest request);
    AcademicSessionResponse update(Long id, AcademicSessionRequest request);
    AcademicSessionResponse getById(Long id);
    Page<AcademicSessionResponse> getAll(Pageable pageable);
    List<AcademicSessionResponse> getActiveSessions();
    void delete(Long id);
}
