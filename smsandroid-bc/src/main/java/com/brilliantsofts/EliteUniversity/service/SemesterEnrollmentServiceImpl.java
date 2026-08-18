package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.SemesterEnrollmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SemesterEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterEnrollment;
import com.brilliantsofts.EliteUniversity.repository.SemesterEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterEnrollmentServiceImpl implements SemesterEnrollmentService {

    private final SemesterEnrollmentRepository repository;

    @Override
    public SemesterEnrollmentResponse create(SemesterEnrollmentRequest request) {
        SemesterEnrollment entity = SemesterEnrollmentMapper.toEntity(request);
        return SemesterEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterEnrollmentResponse update(Long id, SemesterEnrollmentRequest request) {
        SemesterEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SemesterEnrollment not found with id: " + id));
        entity.setStudentId(request.getStudentId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setProgramId(request.getProgramId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setAdvisorId(request.getAdvisorId());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        entity.setRegisteredCredits(request.getRegisteredCredits());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        if (request.getRemarks() != null) entity.setRemarks(request.getRemarks());
        return SemesterEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterEnrollmentResponse getById(Long id) {
        SemesterEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SemesterEnrollment not found with id: " + id));
        return SemesterEnrollmentMapper.toResponse(entity);
    }

    @Override
    public Page<SemesterEnrollmentResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(SemesterEnrollmentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SemesterEnrollment not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public java.util.Map<String, Object> getEligibility(Long studentId, Long semesterId) {
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("eligible", true);
        return result;
    }

    @Override
    public void enroll(java.util.Map<String, Object> request) {
        // Enroll logic
    }

    @Override
    public void forceEnroll(java.util.Map<String, Object> request) {
        // Force enroll logic
    }

    @Override
    public java.util.Map<String, Object> getByStudent(Long studentId) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.Map<String, Object> getByStudentAndSemester(Long studentId, Long semesterId) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getPendingBySemester(Long semesterId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getPendingByAdvisor(Long advisorId, Long semesterId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public void approve(java.util.Map<String, Object> request) {
        // Approve logic
    }

    @Override
    public void cancel(Long enrollmentId) {
        // Cancel logic
    }

    @Override
    public void reopen(Long enrollmentId) {
        // Reopen logic
    }

    @Override
    public void finalizeEnrollment(Long enrollmentId) {
        // Finalize logic
    }

    @Override
    public java.util.Map<String, Object> getDashboard(Long semesterId) {
        return new java.util.HashMap<>();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getHistoryByStudent(Long studentId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getHistoryBySemester(Long semesterId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> getHistoryByEnrollment(Long enrollmentId) {
        return java.util.Collections.emptyList();
    }
}
