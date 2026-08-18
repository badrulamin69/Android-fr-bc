package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.PdfNoteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PdfNoteResponse;
import com.brilliantsofts.EliteUniversity.entity.PdfNote;
import com.brilliantsofts.EliteUniversity.dto.mapper.PdfNoteMapper;
import com.brilliantsofts.EliteUniversity.repository.CourseModuleRepository;
import com.brilliantsofts.EliteUniversity.repository.PdfNoteRepository;
import com.brilliantsofts.EliteUniversity.service.PdfNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfNoteServiceImpl implements PdfNoteService {
    @Autowired
    private PdfNoteRepository repository;
    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Override
    public PdfNoteResponse create(PdfNoteRequest request) {
        PdfNote entity = PdfNoteMapper.toEntity(request);
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        return PdfNoteMapper.toResponse(repository.save(entity));
    }
    @Override
    public PdfNoteResponse update(Long id, PdfNoteRequest request) {
        PdfNote entity = repository.findById(id).orElseThrow(() -> new RuntimeException("PdfNote not found"));
        entity.setTitle(request.getTitle());
        entity.setFileUrl(request.getFileUrl());
        if (request.getModuleId() != null) entity.setModule(courseModuleRepository.findById(request.getModuleId()).orElse(null));
        return PdfNoteMapper.toResponse(repository.save(entity));
    }
    @Override
    public PdfNoteResponse getById(Long id) {
        return PdfNoteMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("PdfNote not found")));
    }
    @Override
    public List<PdfNoteResponse> getAll() {
        return repository.findAll().stream().map(PdfNoteMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<PdfNoteResponse> getByModule(Long moduleId) {
        return repository.findByModuleId(moduleId).stream().map(PdfNoteMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
