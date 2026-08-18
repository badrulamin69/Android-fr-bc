package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.StudentEnrollmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.StudentEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentEnrollment;
import com.brilliantsofts.EliteUniversity.repository.StudentEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final StudentEnrollmentRepository repository;

    @Override
    public StudentEnrollmentResponse create(StudentEnrollmentRequest request) {
        StudentEnrollment entity = StudentEnrollmentMapper.toEntity(request);
        return StudentEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentEnrollmentResponse update(Long id, StudentEnrollmentRequest request) {
        StudentEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentEnrollment not found with id: " + id));
        entity.setStudentId(request.getStudentId());
        entity.setBatchId(request.getBatchId());
        entity.setSectionId(request.getSectionId());
        entity.setEnrollmentDate(request.getEnrollmentDate());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        return StudentEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentEnrollmentResponse getById(Long id) {
        StudentEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentEnrollment not found with id: " + id));
        return StudentEnrollmentMapper.toResponse(entity);
    }

    @Override
    public Page<StudentEnrollmentResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(StudentEnrollmentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("StudentEnrollment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
