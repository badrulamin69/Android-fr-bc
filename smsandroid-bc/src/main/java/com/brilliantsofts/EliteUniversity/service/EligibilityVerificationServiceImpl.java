package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.EligibilityVerificationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EligibilityVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityVerificationResponse;
import com.brilliantsofts.EliteUniversity.entity.EligibilityVerification;
import com.brilliantsofts.EliteUniversity.repository.EligibilityVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EligibilityVerificationServiceImpl implements EligibilityVerificationService {

    private final EligibilityVerificationRepository repository;

    @Override
    public EligibilityVerificationResponse verify(EligibilityVerificationRequest request) {
        EligibilityVerification entity = EligibilityVerificationMapper.toEntity(request);
        entity.setVerifiedAt(LocalDateTime.now());
        if (entity.getStatus() == null) entity.setStatus("VERIFIED");
        return EligibilityVerificationMapper.toResponse(repository.save(entity));
    }

    @Override
    public List<EligibilityVerificationResponse> autoVerifyAll(Long testId) {
        List<EligibilityVerification> verifications = repository.findByTestId(testId);
        return verifications.stream().map(v -> {
            v.setStatus("AUTO_VERIFIED");
            v.setVerifiedAt(LocalDateTime.now());
            return EligibilityVerificationMapper.toResponse(repository.save(v));
        }).collect(Collectors.toList());
    }

    @Override
    public EligibilityVerificationResponse getById(Long id) {
        EligibilityVerification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EligibilityVerification not found with id: " + id));
        return EligibilityVerificationMapper.toResponse(entity);
    }

    @Override
    public Page<EligibilityVerificationResponse> getAll(String search, Long testId, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(EligibilityVerificationMapper::toResponse);
    }

    @Override
    public List<EligibilityVerificationResponse> getByTestId(Long testId) {
        return repository.findByTestId(testId).stream()
                .map(EligibilityVerificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getStats(Long testId) {
        Map<String, Object> stats = new HashMap<>();
        long total = repository.findByTestId(testId).size();
        long verified = repository.countByTestIdAndStatus(testId, "VERIFIED");
        long autoVerified = repository.countByTestIdAndStatus(testId, "AUTO_VERIFIED");
        long pending = repository.countByTestIdAndStatus(testId, "PENDING");
        long rejected = repository.countByTestIdAndStatus(testId, "REJECTED");
        stats.put("total", total);
        stats.put("verified", verified);
        stats.put("autoVerified", autoVerified);
        stats.put("pending", pending);
        stats.put("rejected", rejected);
        return stats;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("EligibilityVerification not found with id: " + id);
        }
        repository.deleteById(id);
    }
}