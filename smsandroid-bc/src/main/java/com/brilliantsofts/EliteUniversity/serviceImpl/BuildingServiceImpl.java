package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.BuildingMapper;
import com.brilliantsofts.EliteUniversity.dto.request.BuildingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BuildingResponse;
import com.brilliantsofts.EliteUniversity.entity.Building;
import com.brilliantsofts.EliteUniversity.repository.BuildingRepository;
import com.brilliantsofts.EliteUniversity.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository repository;

    @Override
    public BuildingResponse create(BuildingRequest request) {
        Building entity = BuildingMapper.toEntity(request);
        return BuildingMapper.toResponse(repository.save(entity));
    }

    @Override
    public BuildingResponse update(Long id, BuildingRequest request) {
        Building entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Building not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setAddress(request.getAddress());
        entity.setTotalFloors(request.getTotalFloors());
        entity.setTotalRooms(request.getTotalRooms());
        entity.setContactPerson(request.getContactPerson());
        entity.setContactPhone(request.getContactPhone());
        entity.setActive(request.isActive());
        return BuildingMapper.toResponse(repository.save(entity));
    }

    @Override
    public BuildingResponse getById(Long id) {
        return BuildingMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Building not found")));
    }

    @Override
    public List<BuildingResponse> getAll() {
        return repository.findByIsActiveTrue().stream()
                .map(BuildingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
