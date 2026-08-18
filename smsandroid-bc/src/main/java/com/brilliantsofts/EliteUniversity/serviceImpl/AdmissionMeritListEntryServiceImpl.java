package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListEntryResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritListEntry;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionMeritListEntryMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListEntryRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionMeritListEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionMeritListEntryServiceImpl implements AdmissionMeritListEntryService {
    @Autowired
    private AdmissionMeritListEntryRepository repository;

    @Override
    public AdmissionMeritListEntryResponse create(Long meritListId, AdmissionMeritListEntryRequest request) {
        AdmissionMeritListEntry entity = AdmissionMeritListEntryMapper.toEntity(request);
        entity.setMeritListId(meritListId);
        return AdmissionMeritListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListEntryResponse update(Long entryId, AdmissionMeritListEntryRequest request) {
        AdmissionMeritListEntry entity = repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionMeritListEntry not found"));
        entity.setRegistrationId(request.getRegistrationId());
        entity.setRank(request.getRank());
        entity.setRollNumber(request.getRollNumber());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setApplicantName(request.getApplicantName());
        entity.setFacultyName(request.getFacultyName());
        entity.setDepartmentName(request.getDepartmentName());
        entity.setProgramName(request.getProgramName());
        entity.setShift(request.getShift());
        entity.setTestMarks(request.getTestMarks());
        entity.setTestMaxMarks(request.getTestMaxMarks());
        entity.setScore(request.getScore());
        entity.setAcademicScore(request.getAcademicScore());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setSscGpa(request.getSscGpa());
        entity.setHscGpa(request.getHscGpa());
        entity.setQuotaType(request.getQuotaType());
        entity.setStatus(request.getStatus());
        entity.setIsOffered(request.getIsOffered());
        entity.setIsEnrolled(request.getIsEnrolled());
        entity.setRemarks(request.getRemarks());
        entity.setSubmittedAt(request.getSubmittedAt());
        return AdmissionMeritListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionMeritListEntryResponse getById(Long entryId) {
        return AdmissionMeritListEntryMapper.toResponse(repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionMeritListEntry not found")));
    }

    @Override
    public Page<AdmissionMeritListEntryResponse> getByMeritListId(Long meritListId, Pageable pageable) {
        return repository.findByMeritListId(meritListId, pageable).map(AdmissionMeritListEntryMapper::toResponse);
    }

    @Override
    public Page<AdmissionMeritListEntryResponse> getByMeritListIdWithFilter(Long meritListId, String search, String status, Pageable pageable) {
        if ((search != null && !search.trim().isEmpty()) || (status != null && !status.trim().isEmpty())) {
            return repository.findByMeritListIdWithFilter(meritListId, search, status, pageable).map(AdmissionMeritListEntryMapper::toResponse);
        }
        return repository.findByMeritListId(meritListId, pageable).map(AdmissionMeritListEntryMapper::toResponse);
    }

    @Override
    public List<AdmissionMeritListEntryResponse> getAllByMeritListId(Long meritListId) {
        return repository.findByMeritListIdOrderByRankAsc(meritListId).stream().map(AdmissionMeritListEntryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AdmissionMeritListEntryResponse updateStatus(Long entryId, String status) {
        AdmissionMeritListEntry entity = repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionMeritListEntry not found"));
        entity.setStatus(status);
        return AdmissionMeritListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long entryId) {
        repository.deleteById(entryId);
    }
}
