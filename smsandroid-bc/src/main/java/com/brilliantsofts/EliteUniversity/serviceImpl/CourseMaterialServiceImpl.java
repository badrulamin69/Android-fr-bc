package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.CourseMaterialMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CourseMaterialRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseMaterialResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseMaterial;
import com.brilliantsofts.EliteUniversity.repository.CourseMaterialRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectRepository;
import com.brilliantsofts.EliteUniversity.service.CourseMaterialService;
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
public class CourseMaterialServiceImpl implements CourseMaterialService {
    @Autowired
    private CourseMaterialRepository repository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public CourseMaterialResponse create(CourseMaterialRequest request) {
        CourseMaterial entity = CourseMaterialMapper.toEntity(request);
        entity.setUniqueCode("MAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        return CourseMaterialMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseMaterialResponse update(Long id, CourseMaterialRequest request) {
        CourseMaterial entity = repository.findById(id).orElseThrow(() -> new RuntimeException("CourseMaterial not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setMaterialType(request.getMaterialType());
        entity.setFileUrl(request.getFileUrl());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        return CourseMaterialMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseMaterialResponse getById(Long id) {
        return CourseMaterialMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("CourseMaterial not found")));
    }

    @Override
    public Page<CourseMaterialResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<CourseMaterial> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("materialType")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(CourseMaterialMapper::toResponse);
        }
        return repository.findAll(pageable).map(CourseMaterialMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
