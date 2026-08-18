package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.GuardianRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GuardianResponse;
import com.brilliantsofts.EliteUniversity.entity.Guardian;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.dto.mapper.GuardianMapper;
import com.brilliantsofts.EliteUniversity.repository.GuardianRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GuardianServiceImpl implements GuardianService {
    @Autowired
    private GuardianRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public GuardianResponse create(GuardianRequest request) {
        Guardian entity = GuardianMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return GuardianMapper.toResponse(repository.save(entity));
    }

    @Override
    public GuardianResponse update(Long id, GuardianRequest request) {
        Guardian entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Guardian not found"));
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setRelationship(request.getRelationship());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setOccupation(request.getOccupation());
        entity.setAddress(request.getAddress());
        entity.setIsPrimary(request.getIsPrimary());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return GuardianMapper.toResponse(repository.save(entity));
    }

    @Override
    public GuardianResponse getById(Long id) {
        return GuardianMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Guardian not found")));
    }

    @Override
    public Page<GuardianResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(GuardianMapper::toResponse);
        }
        return repository.findAll(pageable).map(GuardianMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        return stats;
    }
}
