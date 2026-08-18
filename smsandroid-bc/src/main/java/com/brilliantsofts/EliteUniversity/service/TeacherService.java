package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TeacherService {
    TeacherResponse create(TeacherRequest request);
    TeacherResponse update(Long id, TeacherRequest request);
    TeacherResponse getById(Long id);
    Page<TeacherResponse> getAll(String search, Long departmentId, Long facultyId, String designation, String status, Pageable pageable);
    void delete(Long id);
    Map<String, Object> getDashboard();
    
    java.util.List<Map<String, Object>> getDocuments(Long teacherId);
    Map<String, Object> addDocument(Long teacherId, Map<String, Object> document);
    java.util.List<Map<String, Object>> getCourseAssignments(Long teacherId);
    java.util.List<Map<String, Object>> getPublications(Long teacherId);
    java.util.List<Map<String, Object>> getLeaves(Long teacherId);
    java.util.List<Map<String, Object>> getAttendance(Long teacherId);
}
