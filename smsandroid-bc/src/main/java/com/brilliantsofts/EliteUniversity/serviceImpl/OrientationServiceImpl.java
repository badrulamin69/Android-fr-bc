package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.OrientationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.OrientationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OrientationResponse;
import com.brilliantsofts.EliteUniversity.entity.Orientation;
import com.brilliantsofts.EliteUniversity.repository.OrientationRepository;
import com.brilliantsofts.EliteUniversity.service.OrientationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrientationServiceImpl implements OrientationService {

    @Autowired
    private OrientationRepository repository;

    @Override
    public OrientationResponse create(OrientationRequest request) {
        Orientation entity = OrientationMapper.toEntity(request);
        entity.setUniqueCode("ORI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return OrientationMapper.toResponse(repository.save(entity));
    }

    @Override
    public OrientationResponse update(Long id, OrientationRequest request) {
        Orientation entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Orientation not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setScheduledDate(request.getScheduledDate());
        entity.setVenue(request.getVenue());
        entity.setSemesterId(request.getSemesterId());
        entity.setAcademicSessionId(request.getAcademicSessionId());
        entity.setStatus(request.getStatus());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return OrientationMapper.toResponse(repository.save(entity));
    }

    @Override
    public OrientationResponse getById(Long id) {
        return OrientationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Orientation not found")));
    }

    @Override
    public Page<OrientationResponse> getAll(String search, String status, Pageable pageable) {
        return repository.findAllWithSearchAndStatus(search, status, pageable).map(OrientationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
