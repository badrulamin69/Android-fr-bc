package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.EventRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.EventMapper;
import com.brilliantsofts.EliteUniversity.entity.Event;
import com.brilliantsofts.EliteUniversity.repository.EventRepository;
import com.brilliantsofts.EliteUniversity.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository repository;

    @Override
    public EventResponse create(EventRequest request) {
        Event entity = EventMapper.toEntity(request);
        return EventMapper.toResponse(repository.save(entity));
    }
    @Override
    public EventResponse update(Long id, EventRequest request) {
        Event entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setEventType(request.getEventType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setVenue(request.getVenue());
        entity.setClubId(request.getClubId());
        entity.setMaxParticipants(request.getMaxParticipants());
        entity.setRegistrationFee(request.getRegistrationFee());
        entity.setStatus(request.getStatus());
        return EventMapper.toResponse(repository.save(entity));
    }
    @Override
    public EventResponse getById(Long id) {
        return EventMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Event not found")));
    }
    @Override
    public Page<EventResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(EventMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
