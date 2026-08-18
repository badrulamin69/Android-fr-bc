package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.CurriculumMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CurriculumRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CurriculumResponse;
import com.brilliantsofts.EliteUniversity.entity.Curriculum;
import com.brilliantsofts.EliteUniversity.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository repository;

    @Override
    public CurriculumResponse create(CurriculumRequest request) {
        Curriculum entity = CurriculumMapper.toEntity(request);
        return CurriculumMapper.toResponse(repository.save(entity));
    }

    @Override
    public CurriculumResponse update(Long id, CurriculumRequest request) {
        Curriculum entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curriculum not found with id: " + id));
        entity.setProgramId(request.getProgramId());
        entity.setSubjectId(request.getSubjectId());
        entity.setSemesterId(request.getSemesterId());
        entity.setRequired(request.isRequired());
        entity.setOrderNo(request.getOrderNo());
        entity.setCreditHours(request.getCreditHours());
        return CurriculumMapper.toResponse(repository.save(entity));
    }

    @Override
    public CurriculumResponse getById(Long id) {
        Curriculum entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curriculum not found with id: " + id));
        return CurriculumMapper.toResponse(entity);
    }

    @Override
    public Page<CurriculumResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(CurriculumMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Curriculum not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
