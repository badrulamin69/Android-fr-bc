package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionAttendanceMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionAttendance;
import com.brilliantsofts.EliteUniversity.repository.AdmissionAttendanceRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdmissionAttendanceServiceImpl implements AdmissionAttendanceService {

    private final AdmissionAttendanceRepository repository;

    @Override
    @Transactional
    public AdmissionAttendanceResponse create(AdmissionAttendanceRequest request) {
        AdmissionAttendance entity = AdmissionAttendanceMapper.toEntity(request);
        return AdmissionAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionAttendanceResponse update(Long id, AdmissionAttendanceRequest request) {
        AdmissionAttendance entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        entity.setTestId(request.getTestId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAttemptId(request.getAttemptId());
        entity.setStatus(request.getStatus());
        entity.setCheckInTime(request.getCheckInTime());
        entity.setCheckOutTime(request.getCheckOutTime());
        entity.setMarkedById(request.getMarkedById());
        entity.setRemarks(request.getRemarks());
        entity.setQrScanned(request.getQrScanned());
        return AdmissionAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionAttendanceResponse getById(Long id) {
        AdmissionAttendance entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        return AdmissionAttendanceMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionAttendanceResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmissionAttendanceMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmissionAttendanceMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AdmissionAttendanceResponse> findByTestId(Long testId) {
        return repository.findByTestId(testId).stream()
                .map(AdmissionAttendanceMapper::toResponse)
                .toList();
    }

    @Override
    public Map<String, Object> getStatsByTestId(Long testId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.countByTestId(testId));
        stats.put("present", repository.countByTestIdAndStatus(testId, "PRESENT"));
        stats.put("absent", repository.countByTestIdAndStatus(testId, "ABSENT"));
        stats.put("late", repository.countByTestIdAndStatus(testId, "LATE"));
        return stats;
    }

    @Override
    @Transactional
    public AdmissionAttendanceResponse markAttendance(AdmissionAttendanceRequest request) {
        AdmissionAttendance entity = new AdmissionAttendance();
        entity.setTestId(request.getTestId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAttemptId(request.getAttemptId());
        entity.setStatus("PRESENT");
        entity.setCheckInTime(LocalDateTime.now());
        entity.setMarkedById(request.getMarkedById());
        entity.setRemarks(request.getRemarks());
        entity.setQrScanned(request.getQrScanned());
        return AdmissionAttendanceMapper.toResponse(repository.save(entity));
    }
}
