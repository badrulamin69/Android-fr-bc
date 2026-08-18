package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseModuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseModuleResponse;

import java.util.List;

public interface CourseModuleService {
    CourseModuleResponse create(CourseModuleRequest request);
    CourseModuleResponse update(Long id, CourseModuleRequest request);
    CourseModuleResponse getById(Long id);
    List<CourseModuleResponse> getAll();
    List<CourseModuleResponse> getByCourse(Long courseId);
    void delete(Long id);
}
