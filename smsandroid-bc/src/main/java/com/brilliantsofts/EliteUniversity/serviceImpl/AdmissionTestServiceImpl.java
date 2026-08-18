package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTest;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionTestMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionTestServiceImpl implements AdmissionTestService {
    @Autowired
    private AdmissionTestRepository repository;

    @Override
    public AdmissionTestResponse create(AdmissionTestRequest request) {
        AdmissionTest entity = AdmissionTestMapper.toEntity(request);
        return AdmissionTestMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestResponse update(Long id, AdmissionTestRequest request) {
        AdmissionTest entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTest not found"));
        entity.setName(request.getName());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTestType(request.getTestType());
        entity.setTestDate(request.getTestDate());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPassingMarks(request.getPassingMarks());
        entity.setNegativeMarking(request.getNegativeMarking());
        entity.setNegativeMarkValue(request.getNegativeMarkValue());
        entity.setExamCenter(request.getExamCenter());
        entity.setBuilding(request.getBuilding());
        entity.setRoom(request.getRoom());
        entity.setSeatCapacity(request.getSeatCapacity());
        entity.setInstructions(request.getInstructions());
        entity.setDescription(request.getDescription());
        entity.setStatus(request.getStatus());
        return AdmissionTestMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestResponse getById(Long id) {
        return AdmissionTestMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTest not found")));
    }

    @Override
    public Page<AdmissionTestResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionTestMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AdmissionTestResponse publish(Long id) {
        AdmissionTest entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTest not found"));
        entity.setStatus("PUBLISHED");
        return AdmissionTestMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestResponse close(Long id) {
        AdmissionTest entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionTest not found"));
        entity.setStatus("CLOSED");
        return AdmissionTestMapper.toResponse(repository.save(entity));
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        List<AdmissionTest> all = repository.findAll();
        stats.put("total", all.size());
        stats.put("draft", all.stream().filter(t -> "DRAFT".equals(t.getStatus())).count());
        stats.put("published", all.stream().filter(t -> "PUBLISHED".equals(t.getStatus())).count());
        stats.put("closed", all.stream().filter(t -> "CLOSED".equals(t.getStatus())).count());
        return stats;
    }
}
