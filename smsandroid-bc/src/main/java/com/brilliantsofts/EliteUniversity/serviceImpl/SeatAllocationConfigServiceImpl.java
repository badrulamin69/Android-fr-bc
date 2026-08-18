package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SeatAllocationConfigMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.SeatAllocationConfig;
import com.brilliantsofts.EliteUniversity.repository.SeatAllocationConfigRepository;
import com.brilliantsofts.EliteUniversity.service.SeatAllocationConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatAllocationConfigServiceImpl implements SeatAllocationConfigService {

    private final SeatAllocationConfigRepository repository;

    @Override
    @Transactional
    public SeatAllocationConfigResponse create(SeatAllocationConfigRequest request) {
        SeatAllocationConfig entity = SeatAllocationConfigMapper.toEntity(request);
        return SeatAllocationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public SeatAllocationConfigResponse update(Long id, SeatAllocationConfigRequest request) {
        SeatAllocationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation config not found"));
        entity.setSessionId(request.getSessionId());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setAllocationRound(request.getAllocationRound());
        entity.setAutoAllocation(request.getAutoAllocation());
        entity.setManualAllocation(request.getManualAllocation());
        entity.setAllocationStartDate(request.getAllocationStartDate());
        entity.setAllocationEndDate(request.getAllocationEndDate());
        entity.setAcceptDeadlineHours(request.getAcceptDeadlineHours());
        entity.setLockAfterPublish(request.getLockAfterPublish());
        entity.setEnableQuota(request.getEnableQuota());
        entity.setEnableReservedSeats(request.getEnableReservedSeats());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return SeatAllocationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public SeatAllocationConfigResponse getById(Long id) {
        SeatAllocationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation config not found"));
        return SeatAllocationConfigMapper.toResponse(entity);
    }

    @Override
    public Page<SeatAllocationConfigResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(SeatAllocationConfigMapper::toResponse);
        }
        return repository.findAll(pageable).map(SeatAllocationConfigMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public SeatAllocationConfigResponse activate(Long id) {
        SeatAllocationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation config not found"));
        entity.setStatus("ACTIVE");
        return SeatAllocationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public SeatAllocationConfigResponse close(Long id) {
        SeatAllocationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat allocation config not found"));
        entity.setStatus("CLOSED");
        return SeatAllocationConfigMapper.toResponse(repository.save(entity));
    }
}
