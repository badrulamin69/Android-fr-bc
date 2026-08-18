package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ExamRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamService {
    ExamResponse create(ExamRequest request);
    ExamResponse update(Long id, ExamRequest request);
    ExamResponse getById(Long id);
    Page<ExamResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
