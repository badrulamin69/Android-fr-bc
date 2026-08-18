package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CourseMaterialRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseMaterialResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseMaterial;

public class CourseMaterialMapper {
    public static CourseMaterial toEntity(CourseMaterialRequest request) {
        CourseMaterial entity = new CourseMaterial();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setMaterialType(request.getMaterialType());
        entity.setFileUrl(request.getFileUrl());
        return entity;
    }

    public static CourseMaterialResponse toResponse(CourseMaterial entity) {
        CourseMaterialResponse response = new CourseMaterialResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setMaterialType(entity.getMaterialType());
        response.setFileUrl(entity.getFileUrl());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        if (entity.getSubject() != null) {
            response.setSubjectId(entity.getSubject().getId());
            response.setSubjectName(entity.getSubject().getName());
        }
        if (entity.getAdministration() != null) {
            response.setAdministrationId(entity.getAdministration().getId());
            response.setAdministrationName(entity.getAdministration().getFullName());
        }
        return response;
    }
}
