package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExaminationService {
    ExaminationResponse create(ExaminationRequest request);
    ExaminationResponse update(Long id, ExaminationRequest request);
    ExaminationResponse getById(Long id);
    Page<ExaminationResponse> getAll(Pageable pageable);
    List<ExaminationResponse> getByCourse(Long courseId);
    List<ExaminationResponse> getBySemester(String semester);
    void delete(Long id);
}
