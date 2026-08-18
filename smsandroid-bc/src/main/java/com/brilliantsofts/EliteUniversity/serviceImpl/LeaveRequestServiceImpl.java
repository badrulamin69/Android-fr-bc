package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.LeaveRequestMapper;
import com.brilliantsofts.EliteUniversity.dto.request.LeaveRequestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LeaveRequestResponse;
import com.brilliantsofts.EliteUniversity.entity.LeaveRequest;
import com.brilliantsofts.EliteUniversity.repository.LeaveRequestRepository;
import com.brilliantsofts.EliteUniversity.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository repository;

    @Override
    public LeaveRequestResponse create(LeaveRequestRequest request) {
        LeaveRequest entity = LeaveRequestMapper.toEntity(request);
        entity.setUniqueCode("LVR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return LeaveRequestMapper.toResponse(repository.save(entity));
    }

    @Override
    public LeaveRequestResponse update(Long id, LeaveRequestRequest request) {
        LeaveRequest entity = repository.findById(id).orElseThrow(() -> new RuntimeException("LeaveRequest not found"));
        entity.setEmployeeId(request.getEmployeeId());
        entity.setLeaveType(request.getLeaveType());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setReason(request.getReason());
        entity.setStatus(request.getStatus());
        return LeaveRequestMapper.toResponse(repository.save(entity));
    }

    @Override
    public LeaveRequestResponse getById(Long id) {
        return LeaveRequestMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("LeaveRequest not found")));
    }

    @Override
    public Page<LeaveRequestResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(LeaveRequestMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
