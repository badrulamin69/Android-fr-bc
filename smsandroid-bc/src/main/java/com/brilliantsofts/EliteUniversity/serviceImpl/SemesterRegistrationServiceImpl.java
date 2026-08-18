package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SemesterRegistrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SemesterRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRegistrationResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterRegistration;
import com.brilliantsofts.EliteUniversity.repository.BatchRepository;
import com.brilliantsofts.EliteUniversity.repository.SemesterRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.SemesterRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.SemesterRegistrationService;
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
public class SemesterRegistrationServiceImpl implements SemesterRegistrationService {
    @Autowired
    private SemesterRegistrationRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public SemesterRegistrationResponse create(SemesterRegistrationRequest request) {
        SemesterRegistration entity = SemesterRegistrationMapper.toEntity(request);
        entity.setUniqueCode("SR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getBatchId() != null) entity.setBatch(batchRepository.findById(request.getBatchId()).orElse(null));
        return SemesterRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterRegistrationResponse update(Long id, SemesterRegistrationRequest request) {
        SemesterRegistration entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SemesterRegistration not found"));
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getBatchId() != null) entity.setBatch(batchRepository.findById(request.getBatchId()).orElse(null));
        return SemesterRegistrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterRegistrationResponse getById(Long id) {
        return SemesterRegistrationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("SemesterRegistration not found")));
    }

    @Override
    public Page<SemesterRegistrationResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<SemesterRegistration> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("status")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("remarks")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("uniqueCode")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(SemesterRegistrationMapper::toResponse);
        }
        return repository.findAll(pageable).map(SemesterRegistrationMapper::toResponse);
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
