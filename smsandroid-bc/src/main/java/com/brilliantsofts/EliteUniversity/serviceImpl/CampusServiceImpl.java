package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.CampusMapper;
import com.brilliantsofts.EliteUniversity.dto.request.CampusRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CampusResponse;
import com.brilliantsofts.EliteUniversity.entity.Campus;
import com.brilliantsofts.EliteUniversity.repository.CampusRepository;
import com.brilliantsofts.EliteUniversity.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CampusServiceImpl implements CampusService {

    @Autowired
    private CampusRepository repository;

    @Override
    public CampusResponse create(CampusRequest request) {
        Campus entity = CampusMapper.toEntity(request);
        entity.setUniqueCode("CMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return CampusMapper.toResponse(repository.save(entity));
    }

    @Override
    public CampusResponse update(Long id, CampusRequest request) {
        Campus entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Campus not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setCampusType(request.getCampusType());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setActive(request.isActive());
        return CampusMapper.toResponse(repository.save(entity));
    }

    @Override
    public CampusResponse getById(Long id) {
        return CampusMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Campus not found")));
    }

    @Override
    public Page<CampusResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(CampusMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
