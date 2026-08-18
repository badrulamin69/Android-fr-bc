package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.CourseRegistrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseRegistration;
import com.brilliantsofts.EliteUniversity.repository.BatchRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.SemesterRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.CourseRegistrationService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    @Autowired
    private CourseRegistrationRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public CourseRegistrationResponse create(CourseRegistrationRequest request) {
        CourseRegistration entity = CourseRegistrationMapper.toEntity(request);
        entity.setUniqueCode("CR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getBatchId() != null) entity.setBatch(batchRepository.findById(request.getBatchId()).orElse(null));
        return CourseRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseRegistrationResponse update(Long id, CourseRegistrationRequest request) {
        CourseRegistration entity = repository.findById(id).orElseThrow(() -> new RuntimeException("CourseRegistration not found"));
        entity.setStatus(request.getStatus());
        entity.setCreditHours(request.getCreditHours());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getBatchId() != null) entity.setBatch(batchRepository.findById(request.getBatchId()).orElse(null));
        return CourseRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseRegistrationResponse getById(Long id) {
        return CourseRegistrationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("CourseRegistration not found")));
    }

    @Override
    public Page<CourseRegistrationResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<CourseRegistration> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("status")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("remarks")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("uniqueCode")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(CourseRegistrationMapper::toResponse);
        }
        return repository.findAll(pageable).map(CourseRegistrationMapper::toResponse);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        return stats;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
