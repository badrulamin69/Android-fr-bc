package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionInterviewMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionInterviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionInterviewResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionInterview;
import com.brilliantsofts.EliteUniversity.repository.AdmissionInterviewRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionInterviewService;
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
public class AdmissionInterviewServiceImpl implements AdmissionInterviewService {

    private final AdmissionInterviewRepository repository;

    @Override
    @Transactional
    public AdmissionInterviewResponse create(AdmissionInterviewRequest request) {
        AdmissionInterview entity = AdmissionInterviewMapper.toEntity(request);
        return AdmissionInterviewMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionInterviewResponse update(Long id, AdmissionInterviewRequest request) {
        AdmissionInterview entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission interview not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationId(request.getApplicationId());
        entity.setInterviewerId(request.getInterviewerId());
        entity.setScheduledAt(request.getScheduledAt());
        entity.setCompletedAt(request.getCompletedAt());
        entity.setInterviewType(request.getInterviewType());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setScore(request.getScore());
        entity.setMaxScore(request.getMaxScore());
        entity.setStrengths(request.getStrengths());
        entity.setWeaknesses(request.getWeaknesses());
        entity.setIsRecommended(request.getIsRecommended());
        return AdmissionInterviewMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionInterviewResponse getById(Long id) {
        AdmissionInterview entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission interview not found"));
        return AdmissionInterviewMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionInterviewResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmissionInterviewMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmissionInterviewMapper::toResponse);
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
        stats.put("scheduled", repository.countByStatus("SCHEDULED"));
        stats.put("completed", repository.countByStatus("COMPLETED"));
        stats.put("cancelled", repository.countByStatus("CANCELLED"));
        return stats;
    }
}
