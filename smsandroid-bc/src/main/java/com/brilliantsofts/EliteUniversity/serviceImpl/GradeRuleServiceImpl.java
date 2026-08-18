package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.GradeRuleMapper;
import com.brilliantsofts.EliteUniversity.dto.request.GradeRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GradeRuleResponse;
import com.brilliantsofts.EliteUniversity.entity.GradeRule;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.GradeRuleRepository;
import com.brilliantsofts.EliteUniversity.service.GradeRuleService;
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
public class GradeRuleServiceImpl implements GradeRuleService {
    @Autowired
    private GradeRuleRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public GradeRuleResponse create(GradeRuleRequest request) {
        GradeRule entity = GradeRuleMapper.toEntity(request);
        entity.setUniqueCode("GRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return GradeRuleMapper.toResponse(repository.save(entity));
    }

    @Override
    public GradeRuleResponse update(Long id, GradeRuleRequest request) {
        GradeRule entity = repository.findById(id).orElseThrow(() -> new RuntimeException("GradeRule not found"));
        entity.setGrade(request.getGrade());
        entity.setMinPercentage(request.getMinPercentage());
        entity.setMaxPercentage(request.getMaxPercentage());
        entity.setGradePoint(request.getGradePoint());
        entity.setDescription(request.getDescription());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return GradeRuleMapper.toResponse(repository.save(entity));
    }

    @Override
    public GradeRuleResponse getById(Long id) {
        return GradeRuleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("GradeRule not found")));
    }

    @Override
    public Page<GradeRuleResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<GradeRule> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("grade")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(GradeRuleMapper::toResponse);
        }
        return repository.findAll(pageable).map(GradeRuleMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
