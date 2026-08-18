package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.CourseRegistrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseRegistration;
import com.brilliantsofts.EliteUniversity.repository.CourseRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final CourseRegistrationRepository repository;

    @Override
    public CourseRegistrationResponse selectCourse(CourseRegistrationRequest request) {
        CourseRegistration entity = CourseRegistrationMapper.toEntity(request);
        entity.setRegistrationDate(LocalDateTime.now());
        entity.setStatus("REGISTERED");
        entity.setSelected(true);
        return CourseRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public void dropCourse(Long registrationId) {
        CourseRegistration entity = repository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + registrationId));
        entity.setStatus("DROPPED");
        repository.save(entity);
    }

    @Override
    public CourseRegistrationResponse finalizeRegistration(Long registrationId) {
        CourseRegistration entity = repository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + registrationId));
        entity.setStatus("FINALIZED");
        return CourseRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public Page<CourseRegistrationResponse> getAll(String status, Long semesterId, Pageable pageable) {
        return repository.findAll(pageable)
                .map(CourseRegistrationMapper::toResponse);
    }

    @Override
    public CourseRegistrationResponse getById(Long id) {
        CourseRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
        return CourseRegistrationMapper.toResponse(entity);
    }

    @Override
    public java.util.Map<String, Object> getStudentRegistration(Long studentId, Long semesterId) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.Map<String, Object> getRegistrationSummary(Long studentId, Long semesterId) {
        return new java.util.HashMap<>();
    }

    @Override
    public void processPayment(java.util.Map<String, Object> paymentRequest) {
        // Payment processing logic
    }

    @Override
    public java.util.Map<String, Object> getDashboard(Long semesterId) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.Map<String, Object> checkEligibility(Long studentId, Long semesterId) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("eligible", true);
        return result;
    }

    @Override
    public java.util.Map<String, Object> validateSubject(Long studentId, Long subjectId, Long semesterId) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("valid", true);
        return result;
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getStudentHistory(Long studentId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getSemesterHistory(Long semesterId) {
        return java.util.Collections.emptyList();
    }
}
