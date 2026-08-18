package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPromotionResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherPromotion;

public class TeacherPromotionMapper {
    public static TeacherPromotion toEntity(TeacherPromotionRequest request) {
        TeacherPromotion entity = new TeacherPromotion();
        entity.setTeacherId(request.getTeacherId());
        entity.setPreviousDesignation(request.getPreviousDesignation());
        entity.setNewDesignation(request.getNewDesignation());
        entity.setPreviousDepartment(request.getPreviousDepartment());
        entity.setNewDepartment(request.getNewDepartment());
        entity.setPreviousSalaryGrade(request.getPreviousSalaryGrade());
        entity.setNewSalaryGrade(request.getNewSalaryGrade());
        entity.setPreviousSalary(request.getPreviousSalary());
        entity.setNewSalary(request.getNewSalary());
        entity.setPromotionDate(request.getPromotionDate());
        entity.setReason(request.getReason());
        entity.setApprovedBy(request.getApprovedBy());
        entity.setApprovedByName(request.getApprovedByName());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static TeacherPromotionResponse toResponse(TeacherPromotion entity) {
        TeacherPromotionResponse response = new TeacherPromotionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTeacherId(entity.getTeacherId());
        response.setPreviousDesignation(entity.getPreviousDesignation());
        response.setNewDesignation(entity.getNewDesignation());
        response.setPreviousDepartment(entity.getPreviousDepartment());
        response.setNewDepartment(entity.getNewDepartment());
        response.setPreviousSalaryGrade(entity.getPreviousSalaryGrade());
        response.setNewSalaryGrade(entity.getNewSalaryGrade());
        response.setPreviousSalary(entity.getPreviousSalary());
        response.setNewSalary(entity.getNewSalary());
        response.setPromotionDate(entity.getPromotionDate());
        response.setReason(entity.getReason());
        response.setApprovedBy(entity.getApprovedBy());
        response.setApprovedByName(entity.getApprovedByName());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
