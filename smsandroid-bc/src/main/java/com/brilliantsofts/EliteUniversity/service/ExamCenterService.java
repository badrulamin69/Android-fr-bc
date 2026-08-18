package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ExamCenterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamCenterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamCenterService {
    ExamCenterResponse create(ExamCenterRequest request);
    ExamCenterResponse update(Long id, ExamCenterRequest request);
    ExamCenterResponse getById(Long id);
    Page<ExamCenterResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
