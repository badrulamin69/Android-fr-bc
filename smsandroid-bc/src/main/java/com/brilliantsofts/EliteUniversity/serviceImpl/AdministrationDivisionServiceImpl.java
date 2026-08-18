package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdministrationDivisionMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdministrationDivisionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationDivisionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdministrationDivision;
import com.brilliantsofts.EliteUniversity.repository.AdministrationDivisionRepository;
import com.brilliantsofts.EliteUniversity.service.AdministrationDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdministrationDivisionServiceImpl implements AdministrationDivisionService {

    @Autowired
    private AdministrationDivisionRepository repository;

    @Override
    public AdministrationDivisionResponse create(AdministrationDivisionRequest request) {
        AdministrationDivision entity = AdministrationDivisionMapper.toEntity(request);
        entity.setUniqueCode("ADV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return AdministrationDivisionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdministrationDivisionResponse update(Long id, AdministrationDivisionRequest request) {
        AdministrationDivision entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdministrationDivision not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setDeanName(request.getDeanName());
        entity.setCampusId(request.getCampusId());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return AdministrationDivisionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdministrationDivisionResponse getById(Long id) {
        return AdministrationDivisionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdministrationDivision not found")));
    }

    @Override
    public Page<AdministrationDivisionResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(AdministrationDivisionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
