package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.DepartmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Department;

public class DepartmentMapper {
    public static Department toEntity(DepartmentRequest request) {
        Department entity = new Department();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        return entity;
    }

    public static DepartmentResponse toResponse(Department entity) {
        DepartmentResponse response = new DepartmentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        if (entity.getFaculty() != null) {
            response.setFacultyId(entity.getFaculty().getId());
            response.setFacultyName(entity.getFaculty().getName());
        }
        return response;
    }
}
