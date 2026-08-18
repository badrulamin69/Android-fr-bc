package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCircularRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCircularResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCircular;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionCircularMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionCircularRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionCircularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdmissionCircularServiceImpl implements AdmissionCircularService {
    @Autowired
    private AdmissionCircularRepository repository;

    @Override
    public AdmissionCircularResponse create(AdmissionCircularRequest request) {
        AdmissionCircular entity = AdmissionCircularMapper.toEntity(request);
        return AdmissionCircularMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionCircularResponse update(Long id, AdmissionCircularRequest request) {
        AdmissionCircular entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionCircular not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEligibility(request.getEligibility());
        entity.setRequiredDocuments(request.getRequiredDocuments());
        entity.setAdmissionProcess(request.getAdmissionProcess());
        entity.setPublishDate(request.getPublishDate());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setAttachmentUrl(request.getAttachmentUrl());
        entity.setIsPublished(request.getIsPublished());
        entity.setSessionId(request.getSessionId());
        entity.setProgramId(request.getProgramId());
        return AdmissionCircularMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionCircularResponse getById(Long id) {
        return AdmissionCircularMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionCircular not found")));
    }

    @Override
    public Page<AdmissionCircularResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionCircularMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
