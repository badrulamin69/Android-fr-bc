package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TeacherPublicationMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TeacherPublicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPublicationResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherPublication;
import com.brilliantsofts.EliteUniversity.repository.TeacherPublicationRepository;
import com.brilliantsofts.EliteUniversity.service.TeacherPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherPublicationServiceImpl implements TeacherPublicationService {

    @Autowired
    private TeacherPublicationRepository repository;

    @Override
    public TeacherPublicationResponse create(TeacherPublicationRequest request) {
        TeacherPublication entity = TeacherPublicationMapper.toEntity(request);
        entity.setUniqueCode("PUB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return TeacherPublicationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherPublicationResponse update(Long id, TeacherPublicationRequest request) {
        TeacherPublication entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherPublication not found"));
        entity.setTeacherId(request.getTeacherId());
        entity.setTitle(request.getTitle());
        entity.setAuthors(request.getAuthors());
        entity.setJournal(request.getJournal());
        entity.setPublicationType(request.getPublicationType());
        entity.setPublicationDate(request.getPublicationDate());
        entity.setVolume(request.getVolume());
        entity.setIssue(request.getIssue());
        entity.setPages(request.getPages());
        entity.setDoi(request.getDoi());
        entity.setIsbn(request.getIsbn());
        entity.setAbstractText(request.getAbstractText());
        entity.setUrl(request.getUrl());
        entity.setStatus(request.getStatus());
        return TeacherPublicationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherPublicationResponse getById(Long id) {
        return TeacherPublicationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherPublication not found")));
    }

    @Override
    public Page<TeacherPublicationResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(TeacherPublicationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
