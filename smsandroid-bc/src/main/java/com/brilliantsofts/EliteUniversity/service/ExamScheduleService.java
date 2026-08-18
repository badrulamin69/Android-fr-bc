package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ExamScheduleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamScheduleService {
    ExamScheduleResponse create(ExamScheduleRequest request);
    ExamScheduleResponse update(Long id, ExamScheduleRequest request);
    ExamScheduleResponse getById(Long id);
    Page<ExamScheduleResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
