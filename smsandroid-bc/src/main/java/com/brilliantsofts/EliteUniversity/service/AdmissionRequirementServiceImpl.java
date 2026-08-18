package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionRequirementMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionRequirementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionRequirementResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionRequirement;
import com.brilliantsofts.EliteUniversity.repository.AdmissionRequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionRequirementServiceImpl implements AdmissionRequirementService {

    private final AdmissionRequirementRepository repository;

    @Override
    public AdmissionRequirementResponse create(AdmissionRequirementRequest request) {
        AdmissionRequirement entity = AdmissionRequirementMapper.toEntity(request);
        return AdmissionRequirementMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionRequirementResponse update(Long id, AdmissionRequirementRequest request) {
        AdmissionRequirement entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionRequirement not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setRequirementType(request.getRequirementType());
        entity.setApplicableTo(request.getApplicableTo());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setFacultyId(request.getFacultyId());
        entity.setMandatory(request.isMandatory());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        return AdmissionRequirementMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionRequirementResponse getById(Long id) {
        AdmissionRequirement entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionRequirement not found with id: " + id));
        return AdmissionRequirementMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionRequirementResponse> getAll(String search, String status, Pageable pageable) {
        if (status != null && !status.isEmpty()) {
            return repository.findByStatus(status, pageable)
                    .map(AdmissionRequirementMapper::toResponse);
        }
        return repository.findAllWithSearch(search, pageable)
                .map(AdmissionRequirementMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("AdmissionRequirement not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
