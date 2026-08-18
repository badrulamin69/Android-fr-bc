package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.LiveClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LiveClassResponse;
import com.brilliantsofts.EliteUniversity.entity.LiveClass;
import com.brilliantsofts.EliteUniversity.dto.mapper.LiveClassMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseModuleRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.LiveClassRepository;
import com.brilliantsofts.EliteUniversity.service.LiveClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiveClassServiceImpl implements LiveClassService {
    @Autowired
    private LiveClassRepository repository;
    @Autowired
    private CourseModuleRepository courseModuleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public LiveClassResponse create(LiveClassRequest request) {
        LiveClass entity = LiveClassMapper.toEntity(request);
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        if (request.getTeacherId() != null) entity.setTeacher(employeeRepository.findById(request.getTeacherId()).orElse(null));
        return LiveClassMapper.toResponse(repository.save(entity));
    }
    @Override
    public LiveClassResponse update(Long id, LiveClassRequest request) {
        LiveClass entity = repository.findById(id).orElseThrow(() -> new RuntimeException("LiveClass not found"));
        entity.setTitle(request.getTitle());
        entity.setMeetingUrl(request.getMeetingUrl());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        if (request.getTeacherId() != null) entity.setTeacher(employeeRepository.findById(request.getTeacherId()).orElse(null));
        return LiveClassMapper.toResponse(repository.save(entity));
    }
    @Override
    public LiveClassResponse getById(Long id) {
        return LiveClassMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("LiveClass not found")));
    }
    @Override
    public List<LiveClassResponse> getAll() {
        return repository.findAll().stream().map(LiveClassMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<LiveClassResponse> getByModule(Long moduleId) {
        return repository.findByModuleId(moduleId).stream().map(LiveClassMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<LiveClassResponse> getByTeacher(Long teacherId) {
        return repository.findByTeacherId(teacherId).stream().map(LiveClassMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<LiveClassResponse> getUpcoming() {
        return repository.findAll().stream().map(LiveClassMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
