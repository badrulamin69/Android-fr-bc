package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.GeneratedReportMapper;
import com.brilliantsofts.EliteUniversity.dto.request.GeneratedReportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GeneratedReportResponse;
import com.brilliantsofts.EliteUniversity.entity.GeneratedReport;
import com.brilliantsofts.EliteUniversity.repository.GeneratedReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratedReportServiceImpl implements GeneratedReportService {

    private final GeneratedReportRepository repository;

    @Override
    public GeneratedReportResponse create(GeneratedReportRequest request) {
        GeneratedReport entity = GeneratedReportMapper.toEntity(request);
        return GeneratedReportMapper.toResponse(repository.save(entity));
    }

    @Override
    public GeneratedReportResponse update(Long id, GeneratedReportRequest request) {
        GeneratedReport entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeneratedReport not found with id: " + id));
        entity.setTemplateId(request.getTemplateId());
        entity.setTitle(request.getTitle());
        entity.setReportType(request.getReportType());
        entity.setParameters(request.getParameters());
        entity.setFileUrl(request.getFileUrl());
        entity.setFormat(request.getFormat());
        return GeneratedReportMapper.toResponse(repository.save(entity));
    }

    @Override
    public GeneratedReportResponse getById(Long id) {
        GeneratedReport entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeneratedReport not found with id: " + id));
        return GeneratedReportMapper.toResponse(entity);
    }

    @Override
    public Page<GeneratedReportResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(GeneratedReportMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("GeneratedReport not found with id: " + id);
        }
        repository.deleteById(id);
    }
}