package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionOfferLetterMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionOfferLetterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionOfferLetterResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionOfferLetter;
import com.brilliantsofts.EliteUniversity.repository.AdmissionOfferLetterRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionOfferLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdmissionOfferLetterServiceImpl implements AdmissionOfferLetterService {

    private final AdmissionOfferLetterRepository repository;

    @Override
    @Transactional
    public AdmissionOfferLetterResponse create(AdmissionOfferLetterRequest request) {
        AdmissionOfferLetter entity = AdmissionOfferLetterMapper.toEntity(request);
        return AdmissionOfferLetterMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionOfferLetterResponse update(Long id, AdmissionOfferLetterRequest request) {
        AdmissionOfferLetter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer letter not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setLetterNumber(request.getLetterNumber());
        entity.setApplicationId(request.getApplicationId());
        entity.setMeritListEntryId(request.getMeritListEntryId());
        entity.setIssuedAt(request.getIssuedAt());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setLetterContent(request.getLetterContent());
        entity.setConditions(request.getConditions());
        entity.setRemarks(request.getRemarks());
        entity.setIssuedById(request.getIssuedById());
        entity.setAcceptedAt(request.getAcceptedAt());
        entity.setDeclinedAt(request.getDeclinedAt());
        entity.setDeclineReason(request.getDeclineReason());
        entity.setIsDownloaded(request.getIsDownloaded());
        return AdmissionOfferLetterMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionOfferLetterResponse getById(Long id) {
        AdmissionOfferLetter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer letter not found"));
        return AdmissionOfferLetterMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionOfferLetterResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmissionOfferLetterMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmissionOfferLetterMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AdmissionOfferLetterResponse accept(Long id) {
        AdmissionOfferLetter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer letter not found"));
        entity.setStatus("ACCEPTED");
        entity.setAcceptedAt(LocalDateTime.now());
        return AdmissionOfferLetterMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionOfferLetterResponse decline(Long id, String reason) {
        AdmissionOfferLetter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer letter not found"));
        entity.setStatus("DECLINED");
        entity.setDeclinedAt(LocalDateTime.now());
        entity.setDeclineReason(reason);
        return AdmissionOfferLetterMapper.toResponse(repository.save(entity));
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("issued", repository.countByStatus("ISSUED"));
        stats.put("accepted", repository.countByStatus("ACCEPTED"));
        stats.put("declined", repository.countByStatus("DECLINED"));
        stats.put("expired", repository.countByStatus("EXPIRED"));
        return stats;
    }
}
