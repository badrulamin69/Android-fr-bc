package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ExamMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ExamRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamResponse;
import com.brilliantsofts.EliteUniversity.entity.Exam;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.ExamRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectRepository;
import com.brilliantsofts.EliteUniversity.service.ExamService;
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
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository repository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public ExamResponse create(ExamRequest request) {
        Exam entity = ExamMapper.toEntity(request);
        entity.setUniqueCode("EXM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        return ExamMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamResponse update(Long id, ExamRequest request) {
        Exam entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
        entity.setName(request.getName());
        entity.setExamType(request.getExamType());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassingMarks(request.getPassingMarks());
        entity.setExamDate(request.getExamDate());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setDescription(request.getDescription());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        return ExamMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamResponse getById(Long id) {
        return ExamMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found")));
    }

    @Override
    public Page<ExamResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<Exam> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("examType")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(ExamMapper::toResponse);
        }
        return repository.findAll(pageable).map(ExamMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
