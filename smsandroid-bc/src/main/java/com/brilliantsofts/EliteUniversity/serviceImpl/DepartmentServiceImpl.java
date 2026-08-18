package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.DepartmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Department;
import com.brilliantsofts.EliteUniversity.dto.mapper.DepartmentMapper;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.FacultyRepository;
import com.brilliantsofts.EliteUniversity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        Department entity = DepartmentMapper.toEntity(request);
        if (request.getFacultyId() != null) entity.setFaculty(facultyRepository.findById(request.getFacultyId()).orElse(null));
        return DepartmentMapper.toResponse(repository.save(entity));
    }
    @Override
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        if (request.getFacultyId() != null) entity.setFaculty(facultyRepository.findById(request.getFacultyId()).orElse(null));
        return DepartmentMapper.toResponse(repository.save(entity));
    }
    @Override
    public DepartmentResponse getById(Long id) {
        return DepartmentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found")));
    }
    @Override
    public Page<DepartmentResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(DepartmentMapper::toResponse);
    }
    @Override
    public List<DepartmentResponse> getByFaculty(Long facultyId) {
        return repository.findByFacultyId(facultyId).stream().map(DepartmentMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
