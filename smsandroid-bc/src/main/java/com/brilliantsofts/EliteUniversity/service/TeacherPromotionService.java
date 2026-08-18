package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPromotionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherPromotionService {
    TeacherPromotionResponse create(TeacherPromotionRequest request);
    TeacherPromotionResponse update(Long id, TeacherPromotionRequest request);
    TeacherPromotionResponse getById(Long id);
    Page<TeacherPromotionResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
