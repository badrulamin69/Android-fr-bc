package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ExaminationResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExaminationResultResponse;

import java.util.List;

public interface ExaminationResultService {
    ExaminationResultResponse create(ExaminationResultRequest request);
    ExaminationResultResponse update(Long id, ExaminationResultRequest request);
    ExaminationResultResponse getById(Long id);
    List<ExaminationResultResponse> getAll();
    List<ExaminationResultResponse> getByStudent(Long studentId);
    List<ExaminationResultResponse> getByExamination(Long examinationId);
    void delete(Long id);
}
