package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentIdRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentIdRecordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentIdRecordService {
    StudentIdRecordResponse create(StudentIdRecordRequest request);
    StudentIdRecordResponse update(Long id, StudentIdRecordRequest request);
    StudentIdRecordResponse getById(Long id);
    Page<StudentIdRecordResponse> getAll(String search, String status, Pageable pageable);
    void delete(Long id);
}
