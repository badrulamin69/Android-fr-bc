package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramResponse;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.dto.mapper.ProgramMapper;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    private ProgramRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ProgramResponse create(ProgramRequest request) {
        Program entity = ProgramMapper.toEntity(request);
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return ProgramMapper.toResponse(repository.save(entity));
    }
    @Override
    public ProgramResponse update(Long id, ProgramRequest request) {
        Program entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDurationYears(request.getDurationYears());
        entity.setTotalCredits(request.getTotalCredits());
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return ProgramMapper.toResponse(repository.save(entity));
    }
    @Override
    public ProgramResponse getById(Long id) {
        return ProgramMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Program not found")));
    }
    @Override
    public Page<ProgramResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProgramMapper::toResponse);
    }
    @Override
    public List<ProgramResponse> getByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId).stream().map(ProgramMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
