package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentPromotionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StudentPromotionService {
    StudentPromotionResponse create(StudentPromotionRequest request);
    StudentPromotionResponse update(Long id, StudentPromotionRequest request);
    StudentPromotionResponse getById(Long id);
    Page<StudentPromotionResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
