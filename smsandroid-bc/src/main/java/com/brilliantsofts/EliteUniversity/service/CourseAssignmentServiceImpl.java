package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.CourseAssignmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CourseAssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseAssignmentResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseAssignment;
import com.brilliantsofts.EliteUniversity.repository.CourseAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseAssignmentServiceImpl implements CourseAssignmentService {

    private final CourseAssignmentRepository repository;

    @Override
    public CourseAssignmentResponse create(CourseAssignmentRequest request) {
        CourseAssignment entity = CourseAssignmentMapper.toEntity(request);
        return CourseAssignmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseAssignmentResponse update(Long id, CourseAssignmentRequest request) {
        CourseAssignment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CourseAssignment not found with id: " + id));
        entity.setCourseId(request.getCourseId());
        entity.setSubjectId(request.getSubjectId());
        entity.setAdministrationId(request.getAdministrationId());
        entity.setSemester(request.getSemester());
        return CourseAssignmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseAssignmentResponse getById(Long id) {
        CourseAssignment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CourseAssignment not found with id: " + id));
        return CourseAssignmentMapper.toResponse(entity);
    }

    @Override
    public Page<CourseAssignmentResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(CourseAssignmentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("CourseAssignment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}