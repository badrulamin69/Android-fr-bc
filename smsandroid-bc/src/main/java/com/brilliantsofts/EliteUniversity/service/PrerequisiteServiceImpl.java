package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.PrerequisiteMapper;
import com.brilliantsofts.EliteUniversity.dto.request.PrerequisiteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PrerequisiteResponse;
import com.brilliantsofts.EliteUniversity.entity.Prerequisite;
import com.brilliantsofts.EliteUniversity.repository.PrerequisiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrerequisiteServiceImpl implements PrerequisiteService {

    private final PrerequisiteRepository repository;

    @Override
    public PrerequisiteResponse create(PrerequisiteRequest request) {
        Prerequisite entity = PrerequisiteMapper.toEntity(request);
        return PrerequisiteMapper.toResponse(repository.save(entity));
    }

    @Override
    public PrerequisiteResponse update(Long id, PrerequisiteRequest request) {
        Prerequisite entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prerequisite not found with id: " + id));
        entity.setSubjectId(request.getSubjectId());
        entity.setPrerequisiteSubjectId(request.getPrerequisiteSubjectId());
        entity.setMinGrade(request.getMinGrade());
        entity.setMandatory(request.isMandatory());
        return PrerequisiteMapper.toResponse(repository.save(entity));
    }

    @Override
    public PrerequisiteResponse getById(Long id) {
        Prerequisite entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prerequisite not found with id: " + id));
        return PrerequisiteMapper.toResponse(entity);
    }

    @Override
    public Page<PrerequisiteResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(PrerequisiteMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Prerequisite not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
