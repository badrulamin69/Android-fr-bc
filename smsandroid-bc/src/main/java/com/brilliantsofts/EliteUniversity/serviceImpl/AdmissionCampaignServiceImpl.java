package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionCampaignMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCampaignRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCampaignResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCampaign;
import com.brilliantsofts.EliteUniversity.repository.AdmissionCampaignRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionCampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdmissionCampaignServiceImpl implements AdmissionCampaignService {

    private final AdmissionCampaignRepository repository;

    @Override
    @Transactional
    public AdmissionCampaignResponse create(AdmissionCampaignRequest request) {
        AdmissionCampaign entity = AdmissionCampaignMapper.toEntity(request);
        return AdmissionCampaignMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionCampaignResponse update(Long id, AdmissionCampaignRequest request) {
        AdmissionCampaign entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission campaign not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setDescription(request.getDescription());
        entity.setBudget(request.getBudget());
        entity.setSpent(request.getSpent());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());
        entity.setTargetAudience(request.getTargetAudience());
        entity.setChannels(request.getChannels());
        entity.setApplicationsGenerated(request.getApplicationsGenerated());
        entity.setEnrollmentsConverted(request.getEnrollmentsConverted());
        entity.setNotes(request.getNotes());
        entity.setSessionId(request.getSessionId());
        return AdmissionCampaignMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionCampaignResponse getById(Long id) {
        AdmissionCampaign entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission campaign not found"));
        return AdmissionCampaignMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionCampaignResponse> getAll(int page, int size, String sort, String direction, String search) {
        Sort sortDir = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sortDir);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmissionCampaignMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmissionCampaignMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("active", repository.countByStatus("ACTIVE"));
        stats.put("completed", repository.countByStatus("COMPLETED"));
        stats.put("paused", repository.countByStatus("PAUSED"));
        return stats;
    }
}
