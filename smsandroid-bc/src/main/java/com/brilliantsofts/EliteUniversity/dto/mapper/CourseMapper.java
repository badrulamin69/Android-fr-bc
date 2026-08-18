package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseResponse;
import com.brilliantsofts.EliteUniversity.entity.Course;

public class CourseMapper {
    public static Course toEntity(CourseRequest request) {
        Course entity = new Course();
        entity.setCourseName(request.getCourseName());
        entity.setCourseCode(request.getCourseCode());
        entity.setCredit(request.getCredit());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static CourseResponse toResponse(Course entity) {
        CourseResponse response = new CourseResponse();
        response.setId(entity.getId());
        response.setCourseName(entity.getCourseName());
        response.setCourseCode(entity.getCourseCode());
        response.setCredit(entity.getCredit());
        response.setDescription(entity.getDescription());
        if (entity.getDepartment() != null) {
            response.setDepartmentId(entity.getDepartment().getId());
            response.setDepartmentName(entity.getDepartment().getName());
        }
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
            response.setProgramName(entity.getProgram().getName());
        }
        return response;
    }
}
