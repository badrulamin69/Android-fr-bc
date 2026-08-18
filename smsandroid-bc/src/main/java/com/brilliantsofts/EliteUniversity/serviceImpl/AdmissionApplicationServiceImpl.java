package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionApplicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionApplicationResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionApplication;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionApplicationMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionApplicationRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionApplicationServiceImpl implements AdmissionApplicationService {
    @Autowired
    private AdmissionApplicationRepository repository;

    @Override
    public AdmissionApplicationResponse create(AdmissionApplicationRequest request) {
        AdmissionApplication entity = AdmissionApplicationMapper.toEntity(request);
        return AdmissionApplicationMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionApplicationResponse update(Long id, AdmissionApplicationRequest request) {
        AdmissionApplication entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionApplication not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setCandidateId(request.getCandidateId());
        entity.setSessionId(request.getSessionId());
        entity.setProgramId(request.getProgramId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setCampusId(request.getCampusId());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setSubmittedAt(request.getSubmittedAt());
        entity.setIsSubmitted(request.getIsSubmitted());
        entity.setIsVerified(request.getIsVerified());
        entity.setExamId(request.getExamId());
        entity.setTestScore(request.getTestScore());
        entity.setMeritScore(request.getMeritScore());
        entity.setMeritPosition(request.getMeritPosition());
        return AdmissionApplicationMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionApplicationResponse getById(Long id) {
        return AdmissionApplicationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionApplication not found")));
    }

    @Override
    public Page<AdmissionApplicationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AdmissionApplicationMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<AdmissionApplicationResponse> getUnverified(Pageable pageable) {
        return repository.findByIsVerified(false).stream()
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())))
                .map(AdmissionApplicationMapper::toResponse);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        List<AdmissionApplication> all = repository.findAll();
        stats.put("total", all.size());
        stats.put("submitted", all.stream().filter(a -> Boolean.TRUE.equals(a.getIsSubmitted())).count());
        stats.put("verified", all.stream().filter(a -> Boolean.TRUE.equals(a.getIsVerified())).count());
        stats.put("unverified", all.stream().filter(a -> Boolean.FALSE.equals(a.getIsVerified())).count());
        return stats;
    }
}
