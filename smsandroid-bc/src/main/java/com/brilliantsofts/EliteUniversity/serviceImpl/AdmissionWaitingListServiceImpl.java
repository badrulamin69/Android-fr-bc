package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingList;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionWaitingListMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionWaitingListRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionWaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionWaitingListServiceImpl implements AdmissionWaitingListService {
    @Autowired
    private AdmissionWaitingListRepository repository;

    @Override
    public AdmissionWaitingListResponse create(AdmissionWaitingListRequest request) {
        AdmissionWaitingList entity = AdmissionWaitingListMapper.toEntity(request);
        return AdmissionWaitingListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListResponse update(Long id, AdmissionWaitingListRequest request) {
        AdmissionWaitingList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionWaitingList not found"));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTestId(request.getTestId());
        entity.setStatus(request.getStatus());
        entity.setTotalSlots(request.getTotalSlots());
        entity.setTotalApplicants(request.getTotalApplicants());
        entity.setCutoffScore(request.getCutoffScore());
        entity.setPublishedAt(request.getPublishedAt());
        entity.setRemarks(request.getRemarks());
        return AdmissionWaitingListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListResponse getById(Long id) {
        return AdmissionWaitingListMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionWaitingList not found")));
    }

    @Override
    public Page<AdmissionWaitingListResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionWaitingListMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AdmissionWaitingListResponse generate(Long testId, String name, Integer totalSlots) {
        AdmissionWaitingList entity = new AdmissionWaitingList();
        entity.setName(name);
        entity.setTestId(testId);
        entity.setTotalSlots(totalSlots);
        entity.setStatus("DRAFT");
        entity.setTotalApplicants(0);
        return AdmissionWaitingListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListResponse publish(Long id) {
        AdmissionWaitingList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionWaitingList not found"));
        entity.setStatus("PUBLISHED");
        entity.setPublishedAt(LocalDateTime.now());
        return AdmissionWaitingListMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListResponse unpublish(Long id) {
        AdmissionWaitingList entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionWaitingList not found"));
        entity.setStatus("DRAFT");
        entity.setPublishedAt(null);
        return AdmissionWaitingListMapper.toResponse(repository.save(entity));
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        List<AdmissionWaitingList> all = repository.findAll();
        stats.put("total", all.size());
        stats.put("draft", all.stream().filter(l -> "DRAFT".equals(l.getStatus())).count());
        stats.put("published", all.stream().filter(l -> "PUBLISHED".equals(l.getStatus())).count());
        return stats;
    }
}
