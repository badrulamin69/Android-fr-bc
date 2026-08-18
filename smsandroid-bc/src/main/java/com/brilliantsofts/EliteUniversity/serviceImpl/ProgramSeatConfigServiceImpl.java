package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ProgramSeatConfigMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.ProgramSeatConfig;
import com.brilliantsofts.EliteUniversity.repository.ProgramSeatConfigRepository;
import com.brilliantsofts.EliteUniversity.service.ProgramSeatConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProgramSeatConfigServiceImpl implements ProgramSeatConfigService {

    private final ProgramSeatConfigRepository repository;

    @Override
    @Transactional
    public ProgramSeatConfigResponse create(ProgramSeatConfigRequest request) {
        ProgramSeatConfig entity = ProgramSeatConfigMapper.toEntity(request);
        return ProgramSeatConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public ProgramSeatConfigResponse update(Long id, ProgramSeatConfigRequest request) {
        ProgramSeatConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program seat config not found"));
        entity.setConfigId(request.getConfigId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTotalSeats(request.getTotalSeats());
        entity.setGeneralSeats(request.getGeneralSeats());
        entity.setQuotaSeats(request.getQuotaSeats());
        entity.setReservedSeats(request.getReservedSeats());
        entity.setAllocatedSeats(request.getAllocatedSeats());
        entity.setWaitingSeats(request.getWaitingSeats());
        entity.setIsActive(request.getIsActive());
        return ProgramSeatConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProgramSeatConfigResponse getById(Long id) {
        ProgramSeatConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program seat config not found"));
        return ProgramSeatConfigMapper.toResponse(entity);
    }

    @Override
    public Page<ProgramSeatConfigResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(ProgramSeatConfigMapper::toResponse);
        }
        return repository.findAll(pageable).map(ProgramSeatConfigMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProgramSeatConfigResponse> findByConfigId(Long configId) {
        return repository.findByConfigId(configId).stream()
                .map(ProgramSeatConfigMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProgramSeatConfigResponse> getAvailable(Long configId) {
        return repository.findByConfigId(configId).stream()
                .filter(p -> p.getIsActive() != null && p.getIsActive())
                .filter(p -> p.getAllocatedSeats() == null || p.getAllocatedSeats() < p.getTotalSeats())
                .map(ProgramSeatConfigMapper::toResponse)
                .toList();
    }

    @Override
    public Map<String, Object> getSummary(Long configId) {
        List<ProgramSeatConfig> configs = repository.findByConfigId(configId);
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalConfigs", configs.size());
        int totalSeats = configs.stream().mapToInt(p -> p.getTotalSeats() != null ? p.getTotalSeats() : 0).sum();
        int allocatedSeats = configs.stream().mapToInt(p -> p.getAllocatedSeats() != null ? p.getAllocatedSeats() : 0).sum();
        summary.put("totalSeats", totalSeats);
        summary.put("allocatedSeats", allocatedSeats);
        summary.put("availableSeats", totalSeats - allocatedSeats);
        return summary;
    }
}
