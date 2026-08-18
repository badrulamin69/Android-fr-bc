package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    CourseResponse update(Long id, CourseRequest request);
    CourseResponse getById(Long id);
    CourseResponse getByCode(String code);
    List<CourseResponse> getAll();
    Page<CourseResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    List<CourseResponse> getByDepartment(Long departmentId);
    List<CourseResponse> getByProgram(Long programId);
    void delete(Long id);
}
