package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherAwardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherAwardResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherAward;

public class TeacherAwardMapper {
    public static TeacherAward toEntity(TeacherAwardRequest request) {
        TeacherAward entity = new TeacherAward();
        entity.setTeacherId(request.getTeacherId());
        entity.setAwardName(request.getAwardName());
        entity.setAwardingBody(request.getAwardingBody());
        entity.setCategory(request.getCategory());
        entity.setAwardDate(request.getAwardDate());
        entity.setDescription(request.getDescription());
        entity.setGrade(request.getGrade());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static TeacherAwardResponse toResponse(TeacherAward entity) {
        TeacherAwardResponse response = new TeacherAwardResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTeacherId(entity.getTeacherId());
        response.setAwardName(entity.getAwardName());
        response.setAwardingBody(entity.getAwardingBody());
        response.setCategory(entity.getCategory());
        response.setAwardDate(entity.getAwardDate());
        response.setDescription(entity.getDescription());
        response.setGrade(entity.getGrade());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
