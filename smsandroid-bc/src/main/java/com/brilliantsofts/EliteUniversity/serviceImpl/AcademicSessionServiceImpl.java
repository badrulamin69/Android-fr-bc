package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicSessionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicSessionResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.dto.mapper.AcademicSessionMapper;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.service.AcademicSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicSessionServiceImpl implements AcademicSessionService {
    @Autowired
    private AcademicSessionRepository repository;

    @Override
    public AcademicSessionResponse create(AcademicSessionRequest request) {
        return AcademicSessionMapper.toResponse(repository.save(AcademicSessionMapper.toEntity(request)));
    }
    @Override
    public AcademicSessionResponse update(Long id, AcademicSessionRequest request) {
        AcademicSession entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicSession not found"));
        entity.setSessionName(request.getSessionName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActive(request.isActive());
        return AcademicSessionMapper.toResponse(repository.save(entity));
    }
    @Override
    public AcademicSessionResponse getById(Long id) {
        return AcademicSessionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicSession not found")));
    }
    @Override
    public Page<AcademicSessionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AcademicSessionMapper::toResponse);
    }
    @Override
    public List<AcademicSessionResponse> getActiveSessions() {
        return repository.findByActiveTrue().stream().map(AcademicSessionMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
