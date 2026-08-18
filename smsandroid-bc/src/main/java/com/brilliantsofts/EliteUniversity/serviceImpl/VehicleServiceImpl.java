package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.VehicleMapper;
import com.brilliantsofts.EliteUniversity.dto.request.VehicleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VehicleResponse;
import com.brilliantsofts.EliteUniversity.entity.Vehicle;
import com.brilliantsofts.EliteUniversity.repository.VehicleRepository;
import com.brilliantsofts.EliteUniversity.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    public VehicleResponse create(VehicleRequest request) {
        Vehicle entity = VehicleMapper.toEntity(request);
        entity.setUniqueCode("VEH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return VehicleMapper.toResponse(repository.save(entity));
    }

    @Override
    public VehicleResponse update(Long id, VehicleRequest request) {
        Vehicle entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        entity.setVehicleNumber(request.getVehicleNumber());
        entity.setVehicleType(request.getVehicleType());
        entity.setCapacity(request.getCapacity());
        entity.setDriverName(request.getDriverName());
        entity.setDriverPhone(request.getDriverPhone());
        entity.setActive(Boolean.TRUE.equals(request.getIsActive()));
        return VehicleMapper.toResponse(repository.save(entity));
    }

    @Override
    public VehicleResponse getById(Long id) {
        return VehicleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found")));
    }

    @Override
    public Page<VehicleResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(VehicleMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
