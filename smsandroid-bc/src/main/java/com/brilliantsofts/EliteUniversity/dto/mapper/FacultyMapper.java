package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.FacultyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FacultyResponse;
import com.brilliantsofts.EliteUniversity.entity.Faculty;

public class FacultyMapper {
    public static Faculty toEntity(FacultyRequest request) {
        Faculty entity = new Faculty();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static FacultyResponse toResponse(Faculty entity) {
        FacultyResponse response = new FacultyResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDescription(entity.getDescription());
        return response;
    }
}
