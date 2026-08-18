package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.EmployeeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeResponse;
import com.brilliantsofts.EliteUniversity.entity.Employee;
import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import com.brilliantsofts.EliteUniversity.dto.mapper.EmployeeMapper;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee entity = EmployeeMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return EmployeeMapper.toResponse(repository.save(entity));
    }
    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        entity.setEmployeeId(request.getEmployeeId());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setDesignation(request.getDesignation());
        entity.setEmployeeType(request.getEmployeeType());
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getDepartmentId() != null) entity.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElse(null));
        return EmployeeMapper.toResponse(repository.save(entity));
    }
    @Override
    public EmployeeResponse getById(Long id) {
        return EmployeeMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found")));
    }
    @Override
    public EmployeeResponse getByEmployeeId(String employeeId) {
        return EmployeeMapper.toResponse(repository.findByEmployeeId(employeeId));
    }
    @Override
    public Page<EmployeeResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(EmployeeMapper::toResponse);
    }
    @Override
    public List<EmployeeResponse> getByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId).stream().map(EmployeeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<EmployeeResponse> getByType(EmployeeType type) {
        return repository.findByEmployeeType(type).stream().map(EmployeeMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
