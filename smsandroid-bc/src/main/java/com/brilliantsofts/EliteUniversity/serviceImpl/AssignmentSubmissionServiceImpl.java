package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AssignmentSubmissionMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AssignmentSubmissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentSubmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.AssignmentSubmission;
import com.brilliantsofts.EliteUniversity.repository.AssignmentRepository;
import com.brilliantsofts.EliteUniversity.repository.AssignmentSubmissionRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.AssignmentSubmissionService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentSubmissionServiceImpl implements AssignmentSubmissionService {
    @Autowired
    private AssignmentSubmissionRepository repository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public AssignmentSubmissionResponse create(AssignmentSubmissionRequest request) {
        AssignmentSubmission entity = AssignmentSubmissionMapper.toEntity(request);
        entity.setUniqueCode("SUB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getAssignmentId() != null) entity.setAssignment(assignmentRepository.findById(request.getAssignmentId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return AssignmentSubmissionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AssignmentSubmissionResponse update(Long id, AssignmentSubmissionRequest request) {
        AssignmentSubmission entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AssignmentSubmission not found"));
        entity.setSubmissionDate(request.getSubmissionDate());
        entity.setFileUrl(request.getFileUrl());
        entity.setNotes(request.getNotes());
        entity.setMarksObtained(request.getMarksObtained());
        entity.setFeedback(request.getFeedback());
        entity.setStatus(request.getStatus());
        if (request.getAssignmentId() != null) entity.setAssignment(assignmentRepository.findById(request.getAssignmentId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return AssignmentSubmissionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AssignmentSubmissionResponse getById(Long id) {
        return AssignmentSubmissionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AssignmentSubmission not found")));
    }

    @Override
    public Page<AssignmentSubmissionResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<AssignmentSubmission> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("notes")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("feedback")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("status")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(AssignmentSubmissionMapper::toResponse);
        }
        return repository.findAll(pageable).map(AssignmentSubmissionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
