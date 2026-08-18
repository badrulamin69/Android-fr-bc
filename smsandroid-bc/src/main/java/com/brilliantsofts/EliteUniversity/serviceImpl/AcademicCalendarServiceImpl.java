package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AcademicCalendarMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AcademicCalendarRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicCalendarResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicCalendar;
import com.brilliantsofts.EliteUniversity.repository.AcademicCalendarRepository;
import com.brilliantsofts.EliteUniversity.service.AcademicCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AcademicCalendarServiceImpl implements AcademicCalendarService {

    @Autowired
    private AcademicCalendarRepository repository;

    @Override
    public AcademicCalendarResponse create(AcademicCalendarRequest request) {
        AcademicCalendar entity = AcademicCalendarMapper.toEntity(request);
        entity.setUniqueCode("CAL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return AcademicCalendarMapper.toResponse(repository.save(entity));
    }

    @Override
    public AcademicCalendarResponse update(Long id, AcademicCalendarRequest request) {
        AcademicCalendar entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicCalendar not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEventType(request.getEventType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setSemesterId(request.getSemesterId());
        entity.setHoliday(request.isHoliday());
        entity.setPublished(request.isPublished());
        entity.setColor(request.getColor());
        return AcademicCalendarMapper.toResponse(repository.save(entity));
    }

    @Override
    public AcademicCalendarResponse getById(Long id) {
        return AcademicCalendarMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicCalendar not found")));
    }

    @Override
    public Page<AcademicCalendarResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(AcademicCalendarMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
