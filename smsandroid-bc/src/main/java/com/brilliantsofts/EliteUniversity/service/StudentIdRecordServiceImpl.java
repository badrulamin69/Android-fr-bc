package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.StudentIdRecordMapper;
import com.brilliantsofts.EliteUniversity.dto.request.StudentIdRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentIdRecordResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentIdRecord;
import com.brilliantsofts.EliteUniversity.repository.StudentIdRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentIdRecordServiceImpl implements StudentIdRecordService {

    private final StudentIdRecordRepository repository;

    @Override
    public StudentIdRecordResponse create(StudentIdRecordRequest request) {
        StudentIdRecord entity = StudentIdRecordMapper.toEntity(request);
        return StudentIdRecordMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentIdRecordResponse update(Long id, StudentIdRecordRequest request) {
        StudentIdRecord entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentIdRecord not found with id: " + id));
        entity.setStudentId(request.getStudentId());
        entity.setStudentCode(request.getStudentCode());
        entity.setIdNumber(request.getIdNumber());
        entity.setIdType(request.getIdType());
        if (request.getStatus() != null) entity.setStatus(request.getStatus());
        if (request.getIssuedBy() != null) entity.setIssuedBy(request.getIssuedBy());
        if (request.getRemarks() != null) entity.setRemarks(request.getRemarks());
        return StudentIdRecordMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentIdRecordResponse getById(Long id) {
        StudentIdRecord entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentIdRecord not found with id: " + id));
        return StudentIdRecordMapper.toResponse(entity);
    }

    @Override
    public Page<StudentIdRecordResponse> getAll(String search, String status, Pageable pageable) {
        if (status != null && !status.isEmpty()) {
            return repository.findByStatus(status, pageable)
                    .map(StudentIdRecordMapper::toResponse);
        }
        return repository.findAllWithSearch(search, pageable)
                .map(StudentIdRecordMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("StudentIdRecord not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
