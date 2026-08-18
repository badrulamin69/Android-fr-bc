package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.UniversityMapper;
import com.brilliantsofts.EliteUniversity.dto.request.UniversityRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UniversityResponse;
import com.brilliantsofts.EliteUniversity.entity.University;
import com.brilliantsofts.EliteUniversity.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository repository;

    @Override
    public UniversityResponse create(UniversityRequest request) {
        University entity = UniversityMapper.toEntity(request);
        return UniversityMapper.toResponse(repository.save(entity));
    }

    @Override
    public UniversityResponse update(Long id, UniversityRequest request) {
        University entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setWebsite(request.getWebsite());
        entity.setLogoUrl(request.getLogoUrl());
        entity.setEstablishedYear(request.getEstablishedYear());
        entity.setMotto(request.getMotto());
        entity.setDescription(request.getDescription());
        return UniversityMapper.toResponse(repository.save(entity));
    }

    @Override
    public UniversityResponse getById(Long id) {
        University entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
        return UniversityMapper.toResponse(entity);
    }

    @Override
    public Page<UniversityResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(UniversityMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("University not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
