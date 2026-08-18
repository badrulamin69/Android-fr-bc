package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SeatAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.SeatAllocation;
import com.brilliantsofts.EliteUniversity.repository.SeatAllocationRepository;
import com.brilliantsofts.EliteUniversity.service.SeatAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatAllocationServiceImpl implements SeatAllocationService {

    private final SeatAllocationRepository repository;

    @Override
    @Transactional
    public SeatAllocationResponse create(SeatAllocationRequest request) {
        SeatAllocation entity = SeatAllocationMapper.toEntity(request);
        return SeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public SeatAllocationResponse update(Long id, SeatAllocationRequest request) {
        SeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation not found"));
        entity.setTestId(request.getTestId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setCenterId(request.getCenterId());
        entity.setCenterName(request.getCenterName());
        entity.setBuildingName(request.getBuildingName());
        entity.setRoomName(request.getRoomName());
        entity.setSeatNumber(request.getSeatNumber());
        entity.setRollNumber(request.getRollNumber());
        entity.setStatus(request.getStatus());
        return SeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public SeatAllocationResponse getById(Long id) {
        SeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation not found"));
        return SeatAllocationMapper.toResponse(entity);
    }

    @Override
    public Page<SeatAllocationResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(SeatAllocationMapper::toResponse);
        }
        return repository.findAll(pageable).map(SeatAllocationMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SeatAllocationResponse> findByTestId(Long testId) {
        return repository.findByTestId(testId).stream()
                .map(SeatAllocationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<SeatAllocationResponse> autoGenerate(Long testId) {
        List<SeatAllocation> existing = repository.findByTestId(testId);
        long nextSeat = existing.size() + 1;
        SeatAllocation entity = new SeatAllocation();
        entity.setTestId(testId);
        entity.setSeatNumber(String.valueOf(nextSeat));
        entity.setRollNumber("ROLL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        entity.setStatus("ALLOCATED");
        repository.save(entity);
        return repository.findByTestId(testId).stream()
                .map(SeatAllocationMapper::toResponse)
                .toList();
    }
}
