package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.HostelAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.HostelAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.HostelAllocation;
import com.brilliantsofts.EliteUniversity.repository.HostelAllocationRepository;
import com.brilliantsofts.EliteUniversity.service.HostelAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HostelAllocationServiceImpl implements HostelAllocationService {

    @Autowired
    private HostelAllocationRepository repository;

    @Override
    public HostelAllocationResponse create(HostelAllocationRequest request) {
        HostelAllocation entity = HostelAllocationMapper.toEntity(request);
        entity.setUniqueCode("HA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return HostelAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public HostelAllocationResponse update(Long id, HostelAllocationRequest request) {
        HostelAllocation entity = repository.findById(id).orElseThrow(() -> new RuntimeException("HostelAllocation not found"));
        entity.setStudentId(request.getStudentId());
        entity.setRoomId(request.getRoomId());
        entity.setAllocationDate(request.getAllocationDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());
        entity.setMonthlyRent(request.getMonthlyRent());
        return HostelAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public HostelAllocationResponse getById(Long id) {
        return HostelAllocationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("HostelAllocation not found")));
    }

    @Override
    public Page<HostelAllocationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(HostelAllocationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
