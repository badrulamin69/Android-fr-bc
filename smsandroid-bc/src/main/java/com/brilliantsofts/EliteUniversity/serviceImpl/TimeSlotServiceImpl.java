package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TimeSlotMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TimeSlotRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TimeSlotResponse;
import com.brilliantsofts.EliteUniversity.entity.TimeSlot;
import com.brilliantsofts.EliteUniversity.repository.TimeSlotRepository;
import com.brilliantsofts.EliteUniversity.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository repository;

    @Override
    public TimeSlotResponse create(TimeSlotRequest request) {
        TimeSlot entity = TimeSlotMapper.toEntity(request);
        return TimeSlotMapper.toResponse(repository.save(entity));
    }

    @Override
    public TimeSlotResponse update(Long id, TimeSlotRequest request) {
        TimeSlot entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TimeSlot not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setStartTime(java.time.LocalTime.parse(request.getStartTime()));
        entity.setEndTime(java.time.LocalTime.parse(request.getEndTime()));
        entity.setSlotType(request.getSlotType());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setSortOrder(request.getSortOrder());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return TimeSlotMapper.toResponse(repository.save(entity));
    }

    @Override
    public TimeSlotResponse getById(Long id) {
        return TimeSlotMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TimeSlot not found")));
    }

    @Override
    public List<TimeSlotResponse> getAll() {
        return repository.findByIsActiveTrueOrderBySortOrderAsc().stream()
                .map(TimeSlotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
