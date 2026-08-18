package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.ProgramSeatAllocationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.ProgramSeatAllocation;
import com.brilliantsofts.EliteUniversity.repository.ProgramSeatAllocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProgramSeatAllocationServiceImpl implements ProgramSeatAllocationService {

    private final ProgramSeatAllocationRepository repository;

    @Override
    public ProgramSeatAllocationResponse create(ProgramSeatAllocationRequest request) {
        ProgramSeatAllocation entity = ProgramSeatAllocationMapper.toEntity(request);
        entity.setAllocatedAt(LocalDateTime.now());
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatAllocationResponse getById(Long id) {
        ProgramSeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgramSeatAllocation not found with id: " + id));
        return ProgramSeatAllocationMapper.toResponse(entity);
    }

    @Override
    public Page<ProgramSeatAllocationResponse> getAll(String search, Long configId, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(ProgramSeatAllocationMapper::toResponse);
    }

    @Override
    public Map<String, Object> getStats(Long configId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.countByConfigId(configId));
        stats.put("allocated", repository.countByConfigIdAndStatus(configId, "ALLOCATED"));
        stats.put("confirmed", repository.countByConfigIdAndStatus(configId, "CONFIRMED"));
        stats.put("declined", repository.countByConfigIdAndStatus(configId, "DECLINED"));
        stats.put("cancelled", repository.countByConfigIdAndStatus(configId, "CANCELLED"));
        stats.put("waiting", repository.countByConfigIdAndIsWaiting(configId, true));
        return stats;
    }

    @Override
    public ProgramSeatAllocationResponse manualAllocate(Long registrationId, Long programId, Long configId, String shift, String remarks) {
        ProgramSeatAllocationRequest request = new ProgramSeatAllocationRequest();
        request.setRegistrationId(registrationId);
        request.setAllocatedProgramId(programId);
        request.setConfigId(configId);
        request.setShift(shift);
        request.setRemarks(remarks);
        request.setStatus("ALLOCATED");
        ProgramSeatAllocation entity = ProgramSeatAllocationMapper.toEntity(request);
        entity.setAllocatedAt(LocalDateTime.now());
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatAllocationResponse changeAllocation(Long id, Long newProgramId, String shift, String remarks) {
        ProgramSeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgramSeatAllocation not found with id: " + id));
        entity.setAllocatedProgramId(newProgramId);
        entity.setShift(shift);
        if (remarks != null) entity.setRemarks(remarks);
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatAllocationResponse cancelAllocation(Long id, String remarks) {
        ProgramSeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgramSeatAllocation not found with id: " + id));
        entity.setStatus("CANCELLED");
        if (remarks != null) entity.setRemarks(remarks);
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatAllocationResponse acceptAllocation(Long id) {
        ProgramSeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgramSeatAllocation not found with id: " + id));
        entity.setStatus("CONFIRMED");
        entity.setAcceptedAt(LocalDateTime.now());
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatAllocationResponse declineAllocation(Long id, String remarks) {
        ProgramSeatAllocation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProgramSeatAllocation not found with id: " + id));
        entity.setStatus("DECLINED");
        entity.setDeclinedAt(LocalDateTime.now());
        if (remarks != null) entity.setRemarks(remarks);
        return ProgramSeatAllocationMapper.toResponse(repository.save(entity));
    }

    @Override
    public Map<String, Object> runAutoAllocation(Long configId) {
        Map<String, Object> result = new HashMap<>();
        result.put("totalProcessed", 0);
        result.put("allocated", 0);
        result.put("waiting", 0);
        result.put("notAllocated", 0);
        result.put("round", 1);
        return result;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ProgramSeatAllocation not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
