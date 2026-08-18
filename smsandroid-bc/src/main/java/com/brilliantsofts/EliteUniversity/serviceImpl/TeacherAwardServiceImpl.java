package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TeacherAwardMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TeacherAwardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherAwardResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherAward;
import com.brilliantsofts.EliteUniversity.repository.TeacherAwardRepository;
import com.brilliantsofts.EliteUniversity.service.TeacherAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherAwardServiceImpl implements TeacherAwardService {

    @Autowired
    private TeacherAwardRepository repository;

    @Override
    public TeacherAwardResponse create(TeacherAwardRequest request) {
        TeacherAward entity = TeacherAwardMapper.toEntity(request);
        entity.setUniqueCode("AWD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return TeacherAwardMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherAwardResponse update(Long id, TeacherAwardRequest request) {
        TeacherAward entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherAward not found"));
        entity.setTeacherId(request.getTeacherId());
        entity.setAwardName(request.getAwardName());
        entity.setAwardingBody(request.getAwardingBody());
        entity.setCategory(request.getCategory());
        entity.setAwardDate(request.getAwardDate());
        entity.setDescription(request.getDescription());
        entity.setGrade(request.getGrade());
        entity.setStatus(request.getStatus());
        return TeacherAwardMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherAwardResponse getById(Long id) {
        return TeacherAwardMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherAward not found")));
    }

    @Override
    public Page<TeacherAwardResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(TeacherAwardMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
