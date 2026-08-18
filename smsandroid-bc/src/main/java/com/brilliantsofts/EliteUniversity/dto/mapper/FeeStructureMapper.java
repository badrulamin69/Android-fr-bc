package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.FeeStructureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeStructureResponse;
import com.brilliantsofts.EliteUniversity.entity.FeeStructure;

public class FeeStructureMapper {
    public static FeeStructure toEntity(FeeStructureRequest request) {
        FeeStructure entity = new FeeStructure();
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setProgramId(request.getProgramId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setAmount(request.getAmount());
        entity.setDueDays(request.getDueDays());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static FeeStructureResponse toResponse(FeeStructure entity) {
        FeeStructureResponse response = new FeeStructureResponse();
        response.setId(entity.getId());
        response.setFeeTypeId(entity.getFeeTypeId());
        response.setProgramId(entity.getProgramId());
        response.setSemesterId(entity.getSemesterId());
        response.setBatchId(entity.getBatchId());
        response.setAmount(entity.getAmount());
        response.setDueDays(entity.getDueDays());
        response.setAcademicYear(entity.getAcademicYear());
        response.setDescription(entity.getDescription());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
