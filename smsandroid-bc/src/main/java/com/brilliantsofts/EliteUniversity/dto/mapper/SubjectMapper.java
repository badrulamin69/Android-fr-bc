package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SubjectRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SubjectResponse;
import com.brilliantsofts.EliteUniversity.entity.Subject;

public class SubjectMapper {
    public static Subject toEntity(SubjectRequest request) {
        Subject entity = new Subject();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setCreditHours(request.getCredits());
        return entity;
    }

    public static SubjectResponse toResponse(Subject entity) {
        SubjectResponse response = new SubjectResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setCredits(entity.getCreditHours());
        if (entity.getCourse() != null) response.setCourseId(entity.getCourse().getId());
        return response;
    }
}
