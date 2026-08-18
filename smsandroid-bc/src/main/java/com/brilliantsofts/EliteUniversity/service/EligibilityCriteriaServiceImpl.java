package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.EligibilityCriteriaMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EligibilityCriteriaRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityCriteriaResponse;
import com.brilliantsofts.EliteUniversity.entity.EligibilityCriteria;
import com.brilliantsofts.EliteUniversity.repository.EligibilityCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EligibilityCriteriaServiceImpl implements EligibilityCriteriaService {

    private final EligibilityCriteriaRepository repository;

    @Override
    public EligibilityCriteriaResponse create(EligibilityCriteriaRequest request) {
        EligibilityCriteria entity = EligibilityCriteriaMapper.toEntity(request);
        return EligibilityCriteriaMapper.toResponse(repository.save(entity));
    }

    @Override
    public EligibilityCriteriaResponse update(Long id, EligibilityCriteriaRequest request) {
        EligibilityCriteria entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EligibilityCriteria not found with id: " + id));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setCriteriaType(request.getCriteriaType());
        entity.setMinValue(request.getMinValue());
        entity.setMaxValue(request.getMaxValue());
        entity.setApplicableTo(request.getApplicableTo());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setFacultyId(request.getFacultyId());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        return EligibilityCriteriaMapper.toResponse(repository.save(entity));
    }

    @Override
    public EligibilityCriteriaResponse getById(Long id) {
        EligibilityCriteria entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EligibilityCriteria not found with id: " + id));
        return EligibilityCriteriaMapper.toResponse(entity);
    }

    @Override
    public Page<EligibilityCriteriaResponse> getAll(String search, String status, Pageable pageable) {
        if (status != null && !status.isEmpty()) {
            return repository.findByStatus(status, pageable)
                    .map(EligibilityCriteriaMapper::toResponse);
        }
        return repository.findAllWithSearch(search, pageable)
                .map(EligibilityCriteriaMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("EligibilityCriteria not found with id: " + id);
        }
        repository.deleteById(id);
    }
}