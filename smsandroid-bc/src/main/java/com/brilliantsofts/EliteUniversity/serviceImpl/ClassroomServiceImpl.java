package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ClassroomMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ClassroomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ClassroomResponse;
import com.brilliantsofts.EliteUniversity.entity.Building;
import com.brilliantsofts.EliteUniversity.entity.Classroom;
import com.brilliantsofts.EliteUniversity.repository.BuildingRepository;
import com.brilliantsofts.EliteUniversity.repository.ClassroomRepository;
import com.brilliantsofts.EliteUniversity.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public ClassroomResponse create(ClassroomRequest request) {
        Classroom entity = ClassroomMapper.toEntity(request);
        return ClassroomMapper.toResponse(repository.save(entity));
    }

    @Override
    public ClassroomResponse update(Long id, ClassroomRequest request) {
        Classroom entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Classroom not found"));
        entity.setBuildingId(request.getBuildingId());
        entity.setRoomNumber(request.getRoomNumber());
        entity.setFloor(request.getFloor());
        entity.setCapacity(request.getCapacity());
        entity.setRoomType(request.getRoomType());
        entity.setLab(request.isLab());
        entity.setSmartClassroom(request.isSmartClassroom());
        entity.setHasProjector(request.isHasProjector());
        entity.setHasWhiteboard(request.isHasWhiteboard());
        entity.setHasWifi(request.isHasWifi());
        entity.setEquipment(request.getEquipment());
        entity.setAvailable(request.isAvailable());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return ClassroomMapper.toResponse(repository.save(entity));
    }

    @Override
    public ClassroomResponse getById(Long id) {
        Classroom entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Classroom not found"));
        ClassroomResponse response = ClassroomMapper.toResponse(entity);
        if (entity.getBuildingId() != null) {
            buildingRepository.findById(entity.getBuildingId()).ifPresent(building -> {
                response.setBuildingName(building.getName());
                response.setBuildingCode(building.getCode());
            });
        }
        return response;
    }

    @Override
    public List<ClassroomResponse> getAll() {
        return repository.findByIsActiveTrue().stream()
                .map(entity -> {
                    ClassroomResponse response = ClassroomMapper.toResponse(entity);
                    if (entity.getBuildingId() != null) {
                        buildingRepository.findById(entity.getBuildingId()).ifPresent(building -> {
                            response.setBuildingName(building.getName());
                            response.setBuildingCode(building.getCode());
                        });
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
