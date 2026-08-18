package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.BatchMapper;
import com.brilliantsofts.EliteUniversity.dto.request.BatchRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BatchResponse;
import com.brilliantsofts.EliteUniversity.entity.Batch;
import com.brilliantsofts.EliteUniversity.repository.BatchRepository;
import com.brilliantsofts.EliteUniversity.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository repository;

    @Override
    public BatchResponse create(BatchRequest request) {
        Batch entity = BatchMapper.toEntity(request);
        entity.setUniqueCode("BAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return BatchMapper.toResponse(repository.save(entity));
    }

    @Override
    public BatchResponse update(Long id, BatchRequest request) {
        Batch entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Batch not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setStartYear(request.getStartYear());
        entity.setEndYear(request.getEndYear());
        entity.setCourseId(request.getCourseId());
        return BatchMapper.toResponse(repository.save(entity));
    }

    @Override
    public BatchResponse getById(Long id) {
        return BatchMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Batch not found")));
    }

    @Override
    public Page<BatchResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(BatchMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
