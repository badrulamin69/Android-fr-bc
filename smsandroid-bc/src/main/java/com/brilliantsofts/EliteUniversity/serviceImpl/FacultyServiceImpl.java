package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.FacultyRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FacultyResponse;
import com.brilliantsofts.EliteUniversity.entity.Faculty;
import com.brilliantsofts.EliteUniversity.dto.mapper.FacultyMapper;
import com.brilliantsofts.EliteUniversity.repository.FacultyRepository;
import com.brilliantsofts.EliteUniversity.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository repository;

    @Override
    public FacultyResponse create(FacultyRequest request) {
        return FacultyMapper.toResponse(repository.save(FacultyMapper.toEntity(request)));
    }
    @Override
    public FacultyResponse update(Long id, FacultyRequest request) {
        Faculty entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return FacultyMapper.toResponse(repository.save(entity));
    }
    @Override
    public FacultyResponse getById(Long id) {
        return FacultyMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found")));
    }
    @Override
    public Page<FacultyResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(FacultyMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
