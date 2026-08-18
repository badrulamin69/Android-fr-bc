package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.OnlineClassMapper;
import com.brilliantsofts.EliteUniversity.dto.request.OnlineClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OnlineClassResponse;
import com.brilliantsofts.EliteUniversity.entity.OnlineClass;
import com.brilliantsofts.EliteUniversity.repository.OnlineClassRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectRepository;
import com.brilliantsofts.EliteUniversity.service.OnlineClassService;
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
public class OnlineClassServiceImpl implements OnlineClassService {
    @Autowired
    private OnlineClassRepository repository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public OnlineClassResponse create(OnlineClassRequest request) {
        OnlineClass entity = OnlineClassMapper.toEntity(request);
        entity.setUniqueCode("OLC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        return OnlineClassMapper.toResponse(repository.save(entity));
    }

    @Override
    public OnlineClassResponse update(Long id, OnlineClassRequest request) {
        OnlineClass entity = repository.findById(id).orElseThrow(() -> new RuntimeException("OnlineClass not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setMeetingUrl(request.getMeetingUrl());
        entity.setClassDate(request.getClassDate());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setRecordingUrl(request.getRecordingUrl());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        return OnlineClassMapper.toResponse(repository.save(entity));
    }

    @Override
    public OnlineClassResponse getById(Long id) {
        return OnlineClassMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("OnlineClass not found")));
    }

    @Override
    public Page<OnlineClassResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<OnlineClass> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(OnlineClassMapper::toResponse);
        }
        return repository.findAll(pageable).map(OnlineClassMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
