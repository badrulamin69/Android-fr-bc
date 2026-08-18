package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionTestAttemptMapper;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestAttemptResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestAttempt;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionTestAttemptServiceImpl implements AdmissionTestAttemptService {

    private final AdmissionTestAttemptRepository repository;

    @Override
    public AdmissionTestAttemptResponse getById(Long id) {
        AdmissionTestAttempt entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionTestAttempt not found with id: " + id));
        return AdmissionTestAttemptMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionTestAttemptResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(AdmissionTestAttemptMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("AdmissionTestAttempt not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
