package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.DepartmentAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.DepartmentAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.DepartmentAllocation;
import com.brilliantsofts.EliteUniversity.repository.DepartmentAllocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DepartmentAllocationServiceImpl implements DepartmentAllocationService {

    private final DepartmentAllocationRepository repository;

    @Override
    public DepartmentAllocationResponse create(DepartmentAllocationRequest request) {
        DepartmentAllocation entity = DepartmentAllocationMapper.toEntity(request);
        if (entity.getAllocationNumber() == null || entity.getAllocationNumber().isBlank()) {
            entity.setAllocationNumber("ALLOC-" + System.currentTimeMillis());
        }
        if (entity.getStatus() == null || entity.getStatus().isBlank()) {
            entity.setStatus("ALLOCATED");
        }
        if (entity.getAllocatedAt() == null) {
            entity.setAllocatedAt(LocalDateTime.now());
        }
        return DepartmentAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DepartmentAllocationResponse update(Long id, DepartmentAllocationRequest request) {
        DepartmentAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepartmentAllocation not found with id: " + id));
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAllocatedProgramId(request.getAllocatedProgramId());
        entity.setAllocatedDepartmentId(request.getAllocatedDepartmentId());
        entity.setAllocatedBatchId(request.getAllocatedBatchId());
        entity.setAllocatedSectionId(request.getAllocatedSectionId());
        entity.setSemesterId(request.getSemesterId());
        entity.setAllocatedById(request.getAllocatedById());
        entity.setMeritRank(request.getMeritRank());
        entity.setTotalScore(request.getTotalScore());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        if (request.getRemarks() != null) entity.setRemarks(request.getRemarks());
        return DepartmentAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DepartmentAllocationResponse confirm(Long id) {
        DepartmentAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepartmentAllocation not found with id: " + id));
        entity.setStatus("CONFIRMED");
        entity.setConfirmedAt(LocalDateTime.now());
        return DepartmentAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DepartmentAllocationResponse cancel(Long id) {
        DepartmentAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepartmentAllocation not found with id: " + id));
        entity.setStatus("CANCELLED");
        return DepartmentAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public DepartmentAllocationResponse getById(Long id) {
        DepartmentAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepartmentAllocation not found with id: " + id));
        return DepartmentAllocationMapper.toResponse(entity);
    }

    @Override
    public Page<DepartmentAllocationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(DepartmentAllocationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("DepartmentAllocation not found with id: " + id);
        }
        repository.deleteById(id);
    }
}