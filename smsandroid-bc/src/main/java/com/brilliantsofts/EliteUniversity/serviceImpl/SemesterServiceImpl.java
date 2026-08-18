package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SemesterMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SemesterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterResponse;
import com.brilliantsofts.EliteUniversity.entity.Semester;
import com.brilliantsofts.EliteUniversity.repository.SemesterRepository;
import com.brilliantsofts.EliteUniversity.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository repository;

    @Override
    public SemesterResponse create(SemesterRequest request) {
        Semester entity = SemesterMapper.toEntity(request);
        entity.setUniqueCode("SEM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return SemesterMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterResponse update(Long id, SemesterRequest request) {
        Semester entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Semester not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAcademicSessionId(request.getAcademicSessionId());
        entity.setOrderNo(request.getOrderNo());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setRegistrationDeadline(request.getRegistrationDeadline());
        entity.setStatus(request.getStatus());
        entity.setActive(request.isActive());
        return SemesterMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterResponse getById(Long id) {
        return SemesterMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Semester not found")));
    }

    @Override
    public Page<SemesterResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(SemesterMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
