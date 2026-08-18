package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EmployeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeResponse;
import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse update(Long id, EmployeeRequest request);
    EmployeeResponse getById(Long id);
    EmployeeResponse getByEmployeeId(String employeeId);
    Page<EmployeeResponse> getAll(Pageable pageable);
    List<EmployeeResponse> getByDepartment(Long departmentId);
    List<EmployeeResponse> getByType(EmployeeType type);
    void delete(Long id);
}
