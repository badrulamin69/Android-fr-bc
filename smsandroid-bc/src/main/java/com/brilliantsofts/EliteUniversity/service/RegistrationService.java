package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegistrationService {
    CourseRegistrationResponse selectCourse(CourseRegistrationRequest request);
    void dropCourse(Long registrationId);
    CourseRegistrationResponse finalizeRegistration(Long registrationId);
    Page<CourseRegistrationResponse> getAll(String status, Long semesterId, Pageable pageable);
    CourseRegistrationResponse getById(Long id);

    java.util.Map<String, Object> getStudentRegistration(Long studentId, Long semesterId);
    java.util.Map<String, Object> getRegistrationSummary(Long studentId, Long semesterId);
    void processPayment(java.util.Map<String, Object> paymentRequest);
    java.util.Map<String, Object> getDashboard(Long semesterId);
    java.util.Map<String, Object> checkEligibility(Long studentId, Long semesterId);
    java.util.Map<String, Object> validateSubject(Long studentId, Long subjectId, Long semesterId);
    java.util.List<java.util.Map<String, Object>> getStudentHistory(Long studentId);
    java.util.List<java.util.Map<String, Object>> getSemesterHistory(Long semesterId);
}
