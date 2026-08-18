package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdministrationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdministrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationResponse;
import com.brilliantsofts.EliteUniversity.entity.Administration;
import com.brilliantsofts.EliteUniversity.repository.AdministrationRepository;
import com.brilliantsofts.EliteUniversity.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private AdministrationRepository repository;

    @Override
    public AdministrationResponse create(AdministrationRequest request) {
        Administration entity = AdministrationMapper.toEntity(request);
        entity.setUniqueCode("ADM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return AdministrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdministrationResponse update(Long id, AdministrationRequest request) {
        Administration entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Administration not found"));
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setEmployeeCode(request.getEmployeeCode());
        entity.setQualification(request.getQualification());
        entity.setSpecialization(request.getSpecialization());
        entity.setJoiningDate(request.getJoiningDate());
        entity.setStatus(request.getStatus());
        entity.setUserId(request.getUserId());
        entity.setDepartmentId(request.getDepartmentId());
        return AdministrationMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdministrationResponse getById(Long id) {
        return AdministrationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Administration not found")));
    }

    @Override
    public Page<AdministrationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(AdministrationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
