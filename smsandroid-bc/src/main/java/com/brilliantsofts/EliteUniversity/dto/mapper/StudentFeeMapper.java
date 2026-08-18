package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentFeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentFeeResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentFee;

public class StudentFeeMapper {
    public static StudentFee toEntity(StudentFeeRequest request) {
        StudentFee entity = new StudentFee();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setStudentId(request.getStudentId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setAmount(request.getAmount());
        entity.setDueDate(request.getDueDate());
        entity.setPaidAmount(request.getPaidAmount());
        entity.setStatus(request.getStatus());
        entity.setAcademicYear(request.getAcademicYear());
        return entity;
    }

    public static StudentFeeResponse toResponse(StudentFee entity) {
        StudentFeeResponse response = new StudentFeeResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setFeeTypeId(entity.getFeeTypeId());
        response.setAmount(entity.getAmount());
        response.setDueDate(entity.getDueDate());
        response.setPaidAmount(entity.getPaidAmount());
        response.setStatus(entity.getStatus());
        response.setAcademicYear(entity.getAcademicYear());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
