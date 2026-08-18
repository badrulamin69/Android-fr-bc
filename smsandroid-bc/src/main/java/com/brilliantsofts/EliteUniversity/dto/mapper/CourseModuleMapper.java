package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CourseModuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseModuleResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseModule;

public class CourseModuleMapper {
    public static CourseModule toEntity(CourseModuleRequest request) {
        CourseModule entity = new CourseModule();
        entity.setModuleTitle(request.getModuleTitle());
        entity.setModuleOrder(request.getModuleOrder());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static CourseModuleResponse toResponse(CourseModule entity) {
        CourseModuleResponse response = new CourseModuleResponse();
        response.setId(entity.getId());
        response.setModuleTitle(entity.getModuleTitle());
        response.setModuleOrder(entity.getModuleOrder());
        response.setDescription(entity.getDescription());
        if (entity.getCourse() != null) {
            response.setCourseId(entity.getCourse().getId());
            response.setCourseName(entity.getCourse().getCourseName());
        }
        return response;
    }
}
