package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.CourseModuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseModuleResponse;
import com.brilliantsofts.EliteUniversity.entity.CourseModule;
import com.brilliantsofts.EliteUniversity.dto.mapper.CourseModuleMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseModuleRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.service.CourseModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseModuleServiceImpl implements CourseModuleService {
    @Autowired
    private CourseModuleRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseModuleResponse create(CourseModuleRequest request) {
        CourseModule entity = CourseModuleMapper.toEntity(request);
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return CourseModuleMapper.toResponse(repository.save(entity));
    }
    @Override
    public CourseModuleResponse update(Long id, CourseModuleRequest request) {
        CourseModule entity = repository.findById(id).orElseThrow(() -> new RuntimeException("CourseModule not found"));
        entity.setModuleTitle(request.getModuleTitle());
        entity.setModuleOrder(request.getModuleOrder());
        entity.setDescription(request.getDescription());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        return CourseModuleMapper.toResponse(repository.save(entity));
    }
    @Override
    public CourseModuleResponse getById(Long id) {
        return CourseModuleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("CourseModule not found")));
    }
    @Override
    public List<CourseModuleResponse> getAll() {
        return repository.findAll().stream().map(CourseModuleMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<CourseModuleResponse> getByCourse(Long courseId) {
        return repository.findByCourseId(courseId).stream().map(CourseModuleMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
