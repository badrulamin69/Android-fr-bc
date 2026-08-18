package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.GradeRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GradeRuleResponse;
import com.brilliantsofts.EliteUniversity.entity.GradeRule;

public class GradeRuleMapper {
    public static GradeRule toEntity(GradeRuleRequest request) {
        GradeRule entity = new GradeRule();
        entity.setGrade(request.getGrade());
        entity.setMinPercentage(request.getMinPercentage());
        entity.setMaxPercentage(request.getMaxPercentage());
        entity.setGradePoint(request.getGradePoint());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static GradeRuleResponse toResponse(GradeRule entity) {
        GradeRuleResponse response = new GradeRuleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setGrade(entity.getGrade());
        response.setMinPercentage(entity.getMinPercentage());
        response.setMaxPercentage(entity.getMaxPercentage());
        response.setGradePoint(entity.getGradePoint());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        return response;
    }
}
