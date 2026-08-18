package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.NoticeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NoticeResponse;
import com.brilliantsofts.EliteUniversity.entity.Notice;
import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;
import com.brilliantsofts.EliteUniversity.dto.mapper.NoticeMapper;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.FacultyRepository;
import com.brilliantsofts.EliteUniversity.repository.NoticeRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public NoticeResponse create(NoticeRequest request) {
        Notice entity = NoticeMapper.toEntity(request);
        if (request.getCreatedById() != null) entity.setCreatedBy(userRepository.findById(request.getCreatedById()).orElse(null));
        if (request.getFacultyId() != null) entity.setFaculty(facultyRepository.findById(request.getFacultyId()).orElse(null));
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return NoticeMapper.toResponse(repository.save(entity));
    }
    @Override
    public NoticeResponse update(Long id, NoticeRequest request) {
        Notice entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Notice not found"));
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setAttachmentUrl(request.getAttachmentUrl());
        entity.setPublishDate(request.getPublishDate());
        entity.setExpiryDate(request.getExpiryDate());
        entity.setPublished(request.isPublished());
        entity.setAudience(request.getAudience());
        if (request.getCreatedById() != null) entity.setCreatedBy(userRepository.findById(request.getCreatedById()).orElse(null));
        if (request.getFacultyId() != null) entity.setFaculty(facultyRepository.findById(request.getFacultyId()).orElse(null));
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return NoticeMapper.toResponse(repository.save(entity));
    }
    @Override
    public NoticeResponse getById(Long id) {
        return NoticeMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Notice not found")));
    }
    @Override
    public Page<NoticeResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(NoticeMapper::toResponse);
    }
    @Override
    public List<NoticeResponse> getPublished() {
        return repository.findByPublishedTrue().stream().map(NoticeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<NoticeResponse> getByAudience(NoticeAudience audience) {
        return repository.findByAudience(audience).stream().map(NoticeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<NoticeResponse> getByFaculty(Long facultyId) {
        return repository.findByFacultyId(facultyId).stream().map(NoticeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<NoticeResponse> getByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId).stream().map(NoticeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
