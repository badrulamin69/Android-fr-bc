package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentProfileRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentProfileService {
    StudentProfileResponse create(StudentProfileRequest request);
    StudentProfileResponse update(Long id, StudentProfileRequest request);
    StudentProfileResponse getById(Long id);
    Page<StudentProfileResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
