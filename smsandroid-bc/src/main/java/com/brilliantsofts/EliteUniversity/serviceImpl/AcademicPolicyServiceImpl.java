package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AcademicPolicyMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AcademicPolicyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicPolicyResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicPolicy;
import com.brilliantsofts.EliteUniversity.repository.AcademicPolicyRepository;
import com.brilliantsofts.EliteUniversity.service.AcademicPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AcademicPolicyServiceImpl implements AcademicPolicyService {

    @Autowired
    private AcademicPolicyRepository repository;

    @Override
    public AcademicPolicyResponse create(AcademicPolicyRequest request) {
        AcademicPolicy entity = AcademicPolicyMapper.toEntity(request);
        entity.setUniqueCode("POL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return AcademicPolicyMapper.toResponse(repository.save(entity));
    }

    @Override
    public AcademicPolicyResponse update(Long id, AcademicPolicyRequest request) {
        AcademicPolicy entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicPolicy not found"));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPolicyType(request.getPolicyType());
        entity.setPolicyValue(request.getPolicyValue());
        entity.setProgramId(request.getProgramId());
        entity.setActive(request.isActive());
        entity.setEffectiveFrom(request.getEffectiveFrom());
        entity.setEffectiveTo(request.getEffectiveTo());
        return AcademicPolicyMapper.toResponse(repository.save(entity));
    }

    @Override
    public AcademicPolicyResponse getById(Long id) {
        return AcademicPolicyMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicPolicy not found")));
    }

    @Override
    public Page<AcademicPolicyResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(AcademicPolicyMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
