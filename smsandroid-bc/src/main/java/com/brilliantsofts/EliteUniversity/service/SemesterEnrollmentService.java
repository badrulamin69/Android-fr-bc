package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterEnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SemesterEnrollmentService {
    SemesterEnrollmentResponse create(SemesterEnrollmentRequest request);
    SemesterEnrollmentResponse update(Long id, SemesterEnrollmentRequest request);
    SemesterEnrollmentResponse getById(Long id);
    Page<SemesterEnrollmentResponse> getAll(String search, Pageable pageable);
    void delete(Long id);

    java.util.Map<String, Object> getEligibility(Long studentId, Long semesterId);
    void enroll(java.util.Map<String, Object> request);
    void forceEnroll(java.util.Map<String, Object> request);
    java.util.Map<String, Object> getByStudent(Long studentId);
    java.util.Map<String, Object> getByStudentAndSemester(Long studentId, Long semesterId);
    java.util.List<java.util.Map<String, Object>> getPendingBySemester(Long semesterId);
    java.util.List<java.util.Map<String, Object>> getPendingByAdvisor(Long advisorId, Long semesterId);
    void approve(java.util.Map<String, Object> request);
    void cancel(Long enrollmentId);
    void reopen(Long enrollmentId);
    void finalizeEnrollment(Long enrollmentId);
    java.util.Map<String, Object> getDashboard(Long semesterId);
    java.util.List<java.util.Map<String, Object>> getHistoryByStudent(Long studentId);
    java.util.List<java.util.Map<String, Object>> getHistoryBySemester(Long semesterId);
    java.util.List<java.util.Map<String, Object>> getHistoryByEnrollment(Long enrollmentId);
}
