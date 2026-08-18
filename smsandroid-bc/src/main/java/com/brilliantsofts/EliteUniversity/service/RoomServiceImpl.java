package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.RoomMapper;
import com.brilliantsofts.EliteUniversity.dto.request.RoomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoomResponse;
import com.brilliantsofts.EliteUniversity.entity.Room;
import com.brilliantsofts.EliteUniversity.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;

    @Override
    public RoomResponse create(RoomRequest request) {
        Room entity = RoomMapper.toEntity(request);
        return RoomMapper.toResponse(repository.save(entity));
    }

    @Override
    public RoomResponse update(Long id, RoomRequest request) {
        Room entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        entity.setRoomNumber(request.getRoomNumber());
        entity.setFloor(request.getFloor());
        entity.setCapacity(request.getCapacity());
        entity.setCurrentOccupancy(request.getCurrentOccupancy());
        entity.setRoomType(request.getRoomType());
        entity.setMonthlyRent(request.getMonthlyRent());
        entity.setAvailable(request.isAvailable());
        entity.setHostelId(request.getHostelId());
        return RoomMapper.toResponse(repository.save(entity));
    }

    @Override
    public RoomResponse getById(Long id) {
        Room entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return RoomMapper.toResponse(entity);
    }

    @Override
    public Page<RoomResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(RoomMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
