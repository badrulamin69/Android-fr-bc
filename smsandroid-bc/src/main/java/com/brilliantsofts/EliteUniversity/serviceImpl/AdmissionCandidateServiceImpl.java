package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCandidateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCandidateResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCandidate;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionCandidateMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionCandidateRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdmissionCandidateServiceImpl implements AdmissionCandidateService {
    @Autowired
    private AdmissionCandidateRepository repository;

    @Override
    public AdmissionCandidateResponse create(AdmissionCandidateRequest request) {
        AdmissionCandidate entity = AdmissionCandidateMapper.toEntity(request);
        return AdmissionCandidateMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionCandidateResponse update(Long id, AdmissionCandidateRequest request) {
        AdmissionCandidate entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionCandidate not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setGender(request.getGender());
        entity.setAddress(request.getAddress());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setStatus(request.getStatus());
        entity.setAppliedCourseId(request.getAppliedCourseId());
        return AdmissionCandidateMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionCandidateResponse getById(Long id) {
        return AdmissionCandidateMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionCandidate not found")));
    }

    @Override
    public Page<AdmissionCandidateResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionCandidateMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
