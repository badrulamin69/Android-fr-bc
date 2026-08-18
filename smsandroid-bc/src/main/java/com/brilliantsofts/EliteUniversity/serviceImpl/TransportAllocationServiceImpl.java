package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TransportAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TransportAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.TransportAllocation;
import com.brilliantsofts.EliteUniversity.repository.TransportAllocationRepository;
import com.brilliantsofts.EliteUniversity.service.TransportAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransportAllocationServiceImpl implements TransportAllocationService {

    @Autowired
    private TransportAllocationRepository repository;

    @Override
    public TransportAllocationResponse create(TransportAllocationRequest request) {
        TransportAllocation entity = TransportAllocationMapper.toEntity(request);
        entity.setUniqueCode("TA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return TransportAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TransportAllocationResponse update(Long id, TransportAllocationRequest request) {
        TransportAllocation entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TransportAllocation not found"));
        entity.setStudentId(request.getStudentId());
        entity.setRouteId(request.getRouteId());
        entity.setVehicleId(request.getVehicleId());
        entity.setPickupPoint(request.getPickupPoint());
        entity.setDropPoint(request.getDropPoint());
        entity.setMonthlyFee(request.getMonthlyFee());
        entity.setStatus(request.getStatus());
        return TransportAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TransportAllocationResponse getById(Long id) {
        return TransportAllocationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TransportAllocation not found")));
    }

    @Override
    public Page<TransportAllocationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(TransportAllocationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
