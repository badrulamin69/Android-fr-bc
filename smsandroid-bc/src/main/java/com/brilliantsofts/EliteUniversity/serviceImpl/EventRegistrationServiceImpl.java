package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.EventRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventRegistrationResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.EventRegistrationMapper;
import com.brilliantsofts.EliteUniversity.entity.EventRegistration;
import com.brilliantsofts.EliteUniversity.repository.EventRepository;
import com.brilliantsofts.EliteUniversity.repository.EventRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {
    @Autowired
    private EventRegistrationRepository repository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public EventRegistrationResponse create(EventRegistrationRequest request) {
        EventRegistration entity = EventRegistrationMapper.toEntity(request);
        if (request.getEventId() != null) entity.setEvent(eventRepository.findById(request.getEventId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return EventRegistrationMapper.toResponse(repository.save(entity));
    }
    @Override
    public EventRegistrationResponse update(Long id, EventRegistrationRequest request) {
        EventRegistration entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EventRegistration not found"));
        entity.setRegistrationDate(request.getRegistrationDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getEventId() != null) entity.setEvent(eventRepository.findById(request.getEventId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return EventRegistrationMapper.toResponse(repository.save(entity));
    }
    @Override
    public EventRegistrationResponse getById(Long id) {
        return EventRegistrationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("EventRegistration not found")));
    }
    @Override
    public Page<EventRegistrationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(EventRegistrationMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
