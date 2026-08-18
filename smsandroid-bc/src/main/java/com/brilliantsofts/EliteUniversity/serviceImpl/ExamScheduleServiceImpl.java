package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ExamScheduleMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ExamScheduleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamScheduleResponse;
import com.brilliantsofts.EliteUniversity.entity.ExamSchedule;
import com.brilliantsofts.EliteUniversity.repository.ExamRepository;
import com.brilliantsofts.EliteUniversity.repository.ExamScheduleRepository;
import com.brilliantsofts.EliteUniversity.service.ExamScheduleService;
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
public class ExamScheduleServiceImpl implements ExamScheduleService {
    @Autowired
    private ExamScheduleRepository repository;
    @Autowired
    private ExamRepository examRepository;

    @Override
    public ExamScheduleResponse create(ExamScheduleRequest request) {
        ExamSchedule entity = ExamScheduleMapper.toEntity(request);
        entity.setUniqueCode("ESC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        return ExamScheduleMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamScheduleResponse update(Long id, ExamScheduleRequest request) {
        ExamSchedule entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ExamSchedule not found"));
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setVenue(request.getVenue());
        entity.setNotes(request.getNotes());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        return ExamScheduleMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamScheduleResponse getById(Long id) {
        return ExamScheduleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ExamSchedule not found")));
    }

    @Override
    public Page<ExamScheduleResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<ExamSchedule> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("venue")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("notes")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("uniqueCode")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(ExamScheduleMapper::toResponse);
        }
        return repository.findAll(pageable).map(ExamScheduleMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
