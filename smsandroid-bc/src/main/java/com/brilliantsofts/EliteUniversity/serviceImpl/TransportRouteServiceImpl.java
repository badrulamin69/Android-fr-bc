package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TransportRouteMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TransportRouteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportRouteResponse;
import com.brilliantsofts.EliteUniversity.entity.TransportRoute;
import com.brilliantsofts.EliteUniversity.repository.TransportRouteRepository;
import com.brilliantsofts.EliteUniversity.service.TransportRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransportRouteServiceImpl implements TransportRouteService {

    @Autowired
    private TransportRouteRepository repository;

    @Override
    public TransportRouteResponse create(TransportRouteRequest request) {
        TransportRoute entity = TransportRouteMapper.toEntity(request);
        entity.setUniqueCode("TR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return TransportRouteMapper.toResponse(repository.save(entity));
    }

    @Override
    public TransportRouteResponse update(Long id, TransportRouteRequest request) {
        TransportRoute entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TransportRoute not found"));
        entity.setName(request.getName());
        entity.setRouteCode(request.getRouteCode());
        entity.setStartPoint(request.getStartPoint());
        entity.setEndPoint(request.getEndPoint());
        entity.setDistanceKm(request.getDistanceKm());
        entity.setFare(request.getFare());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return TransportRouteMapper.toResponse(repository.save(entity));
    }

    @Override
    public TransportRouteResponse getById(Long id) {
        return TransportRouteMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TransportRoute not found")));
    }

    @Override
    public Page<TransportRouteResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(TransportRouteMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
