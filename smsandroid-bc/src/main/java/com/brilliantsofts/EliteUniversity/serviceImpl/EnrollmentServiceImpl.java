package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Enrollment;
import com.brilliantsofts.EliteUniversity.dto.mapper.EnrollmentMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.EnrollmentRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        Enrollment entity = EnrollmentMapper.toEntity(request);
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return EnrollmentMapper.toResponse(repository.save(entity));
    }
    @Override
    public EnrollmentResponse update(Long id, EnrollmentRequest request) {
        Enrollment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        entity.setEnrollmentDate(request.getEnrollmentDate());
        entity.setSemester(request.getSemester());
        entity.setStatus(request.getStatus());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return EnrollmentMapper.toResponse(repository.save(entity));
    }
    @Override
    public EnrollmentResponse getById(Long id) {
        return EnrollmentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found")));
    }
    @Override
    public Page<EnrollmentResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(EnrollmentMapper::toResponse);
    }
    @Override
    public List<EnrollmentResponse> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(EnrollmentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<EnrollmentResponse> getByCourse(Long courseId) {
        return repository.findByCourseId(courseId).stream().map(EnrollmentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<EnrollmentResponse> getBySemester(Long studentId, String semester) {
        return repository.findByStudentIdAndSemester(studentId, semester).stream().map(EnrollmentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
