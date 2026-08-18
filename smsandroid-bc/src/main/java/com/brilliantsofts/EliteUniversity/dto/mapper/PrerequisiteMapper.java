package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.PrerequisiteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PrerequisiteResponse;
import com.brilliantsofts.EliteUniversity.entity.Prerequisite;

public class PrerequisiteMapper {
    public static Prerequisite toEntity(PrerequisiteRequest request) {
        Prerequisite entity = new Prerequisite();
        entity.setSubjectId(request.getSubjectId());
        entity.setPrerequisiteSubjectId(request.getPrerequisiteSubjectId());
        entity.setMinGrade(request.getMinGrade());
        entity.setMandatory(request.isMandatory());
        return entity;
    }

    public static PrerequisiteResponse toResponse(Prerequisite entity) {
        PrerequisiteResponse response = new PrerequisiteResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setSubjectId(entity.getSubjectId());
        response.setPrerequisiteSubjectId(entity.getPrerequisiteSubjectId());
        response.setMinGrade(entity.getMinGrade());
        response.setMandatory(entity.isMandatory());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
