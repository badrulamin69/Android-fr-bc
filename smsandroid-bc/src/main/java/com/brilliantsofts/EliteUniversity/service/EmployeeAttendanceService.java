package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EmployeeAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeAttendanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeAttendanceService {
    EmployeeAttendanceResponse create(EmployeeAttendanceRequest request);
    EmployeeAttendanceResponse update(Long id, EmployeeAttendanceRequest request);
    EmployeeAttendanceResponse getById(Long id);
    Page<EmployeeAttendanceResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
