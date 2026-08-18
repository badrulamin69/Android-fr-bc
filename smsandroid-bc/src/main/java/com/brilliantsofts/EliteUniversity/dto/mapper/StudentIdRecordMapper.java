package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentIdRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentIdRecordResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentIdRecord;
import java.time.LocalDateTime;

public class StudentIdRecordMapper {
    public static StudentIdRecord toEntity(StudentIdRecordRequest request) {
        StudentIdRecord entity = new StudentIdRecord();
        entity.setStudentId(request.getStudentId());
        entity.setStudentCode(request.getStudentCode());
        entity.setIdNumber(request.getIdNumber());
        entity.setIdType(request.getIdType());
        entity.setStatus(request.getStatus());
        entity.setIssuedBy(request.getIssuedBy());
        entity.setRemarks(request.getRemarks());
        entity.setIssuedAt(LocalDateTime.now());
        return entity;
    }

    public static StudentIdRecordResponse toResponse(StudentIdRecord entity) {
        StudentIdRecordResponse response = new StudentIdRecordResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setStudentCode(entity.getStudentCode());
        response.setIdNumber(entity.getIdNumber());
        response.setIdType(entity.getIdType());
        response.setStatus(entity.getStatus());
        response.setIssuedAt(entity.getIssuedAt());
        response.setIssuedBy(entity.getIssuedBy());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
