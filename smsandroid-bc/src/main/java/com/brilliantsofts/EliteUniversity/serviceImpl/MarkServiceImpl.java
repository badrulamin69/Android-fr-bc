package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.MarkMapper;
import com.brilliantsofts.EliteUniversity.dto.request.MarkRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MarkResponse;
import com.brilliantsofts.EliteUniversity.entity.Mark;
import com.brilliantsofts.EliteUniversity.repository.ExamRepository;
import com.brilliantsofts.EliteUniversity.repository.MarkRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectRepository;
import com.brilliantsofts.EliteUniversity.service.MarkService;
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
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkRepository repository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public MarkResponse create(MarkRequest request) {
        Mark entity = MarkMapper.toEntity(request);
        entity.setUniqueCode("MRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        return MarkMapper.toResponse(repository.save(entity));
    }

    @Override
    public MarkResponse update(Long id, MarkRequest request) {
        Mark entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Mark not found"));
        entity.setMarksObtained(request.getMarksObtained());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setGrade(request.getGrade());
        entity.setRemarks(request.getRemarks());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        return MarkMapper.toResponse(repository.save(entity));
    }

    @Override
    public MarkResponse getById(Long id) {
        return MarkMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Mark not found")));
    }

    @Override
    public Page<MarkResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<Mark> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("grade")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("remarks")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("uniqueCode")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(MarkMapper::toResponse);
        }
        return repository.findAll(pageable).map(MarkMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
