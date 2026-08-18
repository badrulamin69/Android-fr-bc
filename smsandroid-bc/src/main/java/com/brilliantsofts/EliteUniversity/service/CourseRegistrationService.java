package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CourseRegistrationService {
    CourseRegistrationResponse create(CourseRegistrationRequest request);
    CourseRegistrationResponse update(Long id, CourseRegistrationRequest request);
    CourseRegistrationResponse getById(Long id);
    Page<CourseRegistrationResponse> getAll(Pageable pageable, String search);
    Map<String, Object> getStats();
    void delete(Long id);
}
