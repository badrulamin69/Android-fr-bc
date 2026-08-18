package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SectionMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SectionResponse;
import com.brilliantsofts.EliteUniversity.entity.Section;
import com.brilliantsofts.EliteUniversity.repository.SectionRepository;
import com.brilliantsofts.EliteUniversity.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository repository;

    @Override
    public SectionResponse create(SectionRequest request) {
        Section entity = SectionMapper.toEntity(request);
        entity.setUniqueCode("SEC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return SectionMapper.toResponse(repository.save(entity));
    }

    @Override
    public SectionResponse update(Long id, SectionRequest request) {
        Section entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Section not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setBatchId(request.getBatchId());
        return SectionMapper.toResponse(repository.save(entity));
    }

    @Override
    public SectionResponse getById(Long id) {
        return SectionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Section not found")));
    }

    @Override
    public Page<SectionResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(SectionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
