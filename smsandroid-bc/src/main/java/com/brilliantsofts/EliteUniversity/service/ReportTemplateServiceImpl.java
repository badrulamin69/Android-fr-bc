package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.ReportTemplateMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ReportTemplateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ReportTemplateResponse;
import com.brilliantsofts.EliteUniversity.entity.ReportTemplate;
import com.brilliantsofts.EliteUniversity.repository.ReportTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;

    @Override
    public ReportTemplateResponse create(ReportTemplateRequest request) {
        ReportTemplate entity = ReportTemplateMapper.toEntity(request);
        return ReportTemplateMapper.toResponse(repository.save(entity));
    }

    @Override
    public ReportTemplateResponse update(Long id, ReportTemplateRequest request) {
        ReportTemplate entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReportTemplate not found with id: " + id));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setReportType(request.getReportType());
        entity.setTemplateConfig(request.getTemplateConfig());
        entity.setActive(request.isActive());
        return ReportTemplateMapper.toResponse(repository.save(entity));
    }

    @Override
    public ReportTemplateResponse getById(Long id) {
        ReportTemplate entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReportTemplate not found with id: " + id));
        return ReportTemplateMapper.toResponse(entity);
    }

    @Override
    public Page<ReportTemplateResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(ReportTemplateMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ReportTemplate not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
