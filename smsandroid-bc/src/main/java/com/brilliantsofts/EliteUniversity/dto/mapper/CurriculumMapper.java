package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CurriculumRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CurriculumResponse;
import com.brilliantsofts.EliteUniversity.entity.Curriculum;

public class CurriculumMapper {
    public static Curriculum toEntity(CurriculumRequest request) {
        Curriculum entity = new Curriculum();
        entity.setProgramId(request.getProgramId());
        entity.setSubjectId(request.getSubjectId());
        entity.setSemesterId(request.getSemesterId());
        entity.setRequired(request.isRequired());
        entity.setOrderNo(request.getOrderNo());
        entity.setCreditHours(request.getCreditHours());
        return entity;
    }

    public static CurriculumResponse toResponse(Curriculum entity) {
        CurriculumResponse response = new CurriculumResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setProgramId(entity.getProgramId());
        response.setSubjectId(entity.getSubjectId());
        response.setSemesterId(entity.getSemesterId());
        response.setRequired(entity.isRequired());
        response.setOrderNo(entity.getOrderNo());
        response.setCreditHours(entity.getCreditHours());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
