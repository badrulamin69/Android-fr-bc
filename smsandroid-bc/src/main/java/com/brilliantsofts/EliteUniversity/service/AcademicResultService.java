package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicResultResponse;
import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademicResultService {
    AcademicResultResponse create(AcademicResultRequest request);
    AcademicResultResponse update(Long id, AcademicResultRequest request);
    AcademicResultResponse getById(Long id);
    Page<AcademicResultResponse> getAll(Pageable pageable);
    List<AcademicResultResponse> getByApplicant(Long applicantId);
    List<AcademicResultResponse> getByExamType(AcademicExamType type);
    AcademicResultResponse getApplicantExamResult(Long applicantId, AcademicExamType type);
    void delete(Long id);
}
