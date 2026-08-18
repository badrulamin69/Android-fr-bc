package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestQuestionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestQuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmissionTestQuestionService {
    AdmissionTestQuestionResponse create(AdmissionTestQuestionRequest request);
    AdmissionTestQuestionResponse update(Long id, AdmissionTestQuestionRequest request);
    AdmissionTestQuestionResponse getById(Long id);
    Page<AdmissionTestQuestionResponse> getAll(Pageable pageable);
    List<AdmissionTestQuestionResponse> getByTestId(Long testId);
    void delete(Long id);
    long countByTestId(Long testId);
}
