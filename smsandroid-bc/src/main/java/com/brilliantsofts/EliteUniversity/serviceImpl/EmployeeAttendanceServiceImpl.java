package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.EmployeeAttendanceMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EmployeeAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EmployeeAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.EmployeeAttendance;
import com.brilliantsofts.EliteUniversity.repository.EmployeeAttendanceRepository;
import com.brilliantsofts.EliteUniversity.service.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

    @Autowired
    private EmployeeAttendanceRepository repository;

    @Override
    public EmployeeAttendanceResponse create(EmployeeAttendanceRequest request) {
        EmployeeAttendance entity = EmployeeAttendanceMapper.toEntity(request);
        entity.setUniqueCode("EAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return EmployeeAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    public EmployeeAttendanceResponse update(Long id, EmployeeAttendanceRequest request) {
        EmployeeAttendance entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EmployeeAttendance not found"));
        entity.setEmployeeId(request.getEmployeeId());
        entity.setAttendanceDate(request.getAttendanceDate());
        entity.setStatus(request.getStatus());
        entity.setCheckIn(request.getCheckIn());
        entity.setCheckOut(request.getCheckOut());
        entity.setRemarks(request.getRemarks());
        return EmployeeAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    public EmployeeAttendanceResponse getById(Long id) {
        return EmployeeAttendanceMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("EmployeeAttendance not found")));
    }

    @Override
    public Page<EmployeeAttendanceResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(EmployeeAttendanceMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
