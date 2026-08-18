package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.StudentFeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentFeeResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentFee;
import com.brilliantsofts.EliteUniversity.dto.mapper.StudentFeeMapper;
import com.brilliantsofts.EliteUniversity.repository.StudentFeeRepository;
import com.brilliantsofts.EliteUniversity.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentFeeServiceImpl implements StudentFeeService {
    @Autowired
    private StudentFeeRepository repository;

    @Override
    public StudentFeeResponse create(StudentFeeRequest request) {
        return StudentFeeMapper.toResponse(repository.save(StudentFeeMapper.toEntity(request)));
    }

    @Override
    public StudentFeeResponse update(Long id, StudentFeeRequest request) {
        StudentFee entity = repository.findById(id).orElseThrow(() -> new RuntimeException("StudentFee not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setStudentId(request.getStudentId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setAmount(request.getAmount());
        entity.setDueDate(request.getDueDate());
        entity.setPaidAmount(request.getPaidAmount());
        entity.setStatus(request.getStatus());
        entity.setAcademicYear(request.getAcademicYear());
        return StudentFeeMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentFeeResponse getById(Long id) {
        return StudentFeeMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("StudentFee not found")));
    }

    @Override
    public Page<StudentFeeResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(StudentFeeMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
