package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentPromotionResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentPromotion;

import java.util.UUID;

public class StudentPromotionMapper {
    public static StudentPromotion toEntity(StudentPromotionRequest request) {
        StudentPromotion entity = new StudentPromotion();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setFromBatchId(request.getFromBatchId());
        entity.setToBatchId(request.getToBatchId());
        entity.setPromotionDate(request.getPromotionDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static StudentPromotionResponse toResponse(StudentPromotion entity) {
        StudentPromotionResponse response = new StudentPromotionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        if (entity.getFromSemester() != null) {
            response.setFromSemesterId(entity.getFromSemester().getId());
        }
        if (entity.getToSemester() != null) {
            response.setToSemesterId(entity.getToSemester().getId());
        }
        response.setFromBatchId(entity.getFromBatchId());
        response.setToBatchId(entity.getToBatchId());
        response.setPromotionDate(entity.getPromotionDate());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        if (entity.getApprovedBy() != null) {
            response.setApprovedById(entity.getApprovedBy().getId());
        }
        return response;
    }
}
