package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EmployeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeResponse;
import com.brilliantsofts.EliteUniversity.entity.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeRequest request) {
        Employee entity = new Employee();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setDesignation(request.getDesignation());
        entity.setEmployeeType(request.getEmployeeType());
        return entity;
    }

    public static EmployeeResponse toResponse(Employee entity) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(entity.getId());
        response.setEmployeeId(entity.getEmployeeId());
        response.setFullName(entity.getFullName());
        response.setPhone(entity.getPhone());
        response.setDesignation(entity.getDesignation());
        response.setEmployeeType(entity.getEmployeeType());
        response.setStatus("ACTIVE");
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
            response.setEmail(entity.getUser().getEmail());
        }
        if (entity.getDepartment() != null) {
            response.setDepartmentId(entity.getDepartment().getId());
            response.setDepartmentName(entity.getDepartment().getName());
        }
        return response;
    }
}
