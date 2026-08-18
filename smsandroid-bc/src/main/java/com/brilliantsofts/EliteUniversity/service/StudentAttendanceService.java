package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.StudentAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentAttendanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StudentAttendanceService {
    StudentAttendanceResponse create(StudentAttendanceRequest request);
    StudentAttendanceResponse update(Long id, StudentAttendanceRequest request);
    StudentAttendanceResponse getById(Long id);
    Page<StudentAttendanceResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
