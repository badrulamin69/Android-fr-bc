package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherAwardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherAwardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherAwardService {
    TeacherAwardResponse create(TeacherAwardRequest request);
    TeacherAwardResponse update(Long id, TeacherAwardRequest request);
    TeacherAwardResponse getById(Long id);
    Page<TeacherAwardResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
