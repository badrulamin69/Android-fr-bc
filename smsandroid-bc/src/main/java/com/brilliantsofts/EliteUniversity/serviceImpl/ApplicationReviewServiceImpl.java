package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ApplicationReviewMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ApplicationReviewRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicationReviewResponse;
import com.brilliantsofts.EliteUniversity.entity.ApplicationReview;
import com.brilliantsofts.EliteUniversity.repository.ApplicationReviewRepository;
import com.brilliantsofts.EliteUniversity.service.ApplicationReviewService;
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
public class ApplicationReviewServiceImpl implements ApplicationReviewService {

    private final ApplicationReviewRepository repository;

    @Override
    @Transactional
    public ApplicationReviewResponse create(ApplicationReviewRequest request) {
        ApplicationReview entity = ApplicationReviewMapper.toEntity(request);
        return ApplicationReviewMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public ApplicationReviewResponse update(Long id, ApplicationReviewRequest request) {
        ApplicationReview entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application review not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationId(request.getApplicationId());
        entity.setReviewerId(request.getReviewerId());
        entity.setStatus(request.getStatus());
        entity.setComments(request.getComments());
        entity.setRejectionReason(request.getRejectionReason());
        entity.setScore(request.getScore());
        entity.setReviewedAt(request.getReviewedAt());
        entity.setIsRecommended(request.getIsRecommended());
        return ApplicationReviewMapper.toResponse(repository.save(entity));
    }

    @Override
    public ApplicationReviewResponse getById(Long id) {
        ApplicationReview entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application review not found"));
        return ApplicationReviewMapper.toResponse(entity);
    }

    @Override
    public Page<ApplicationReviewResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(ApplicationReviewMapper::toResponse);
        }
        return repository.findAll(pageable).map(ApplicationReviewMapper::toResponse);
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
        stats.put("pending", repository.countByStatus("PENDING"));
        stats.put("approved", repository.countByStatus("APPROVED"));
        stats.put("rejected", repository.countByStatus("REJECTED"));
        return stats;
    }
}
