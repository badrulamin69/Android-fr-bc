package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListEntryResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingListEntry;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionWaitingListEntryMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionWaitingListEntryRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionWaitingListEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionWaitingListEntryServiceImpl implements AdmissionWaitingListEntryService {
    @Autowired
    private AdmissionWaitingListEntryRepository repository;

    @Override
    public AdmissionWaitingListEntryResponse create(Long waitingListId, AdmissionWaitingListEntryRequest request) {
        AdmissionWaitingListEntry entity = AdmissionWaitingListEntryMapper.toEntity(request);
        entity.setWaitingListId(waitingListId);
        return AdmissionWaitingListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListEntryResponse update(Long entryId, AdmissionWaitingListEntryRequest request) {
        AdmissionWaitingListEntry entity = repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionWaitingListEntry not found"));
        entity.setRegistrationId(request.getRegistrationId());
        entity.setRank(request.getRank());
        entity.setRollNumber(request.getRollNumber());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setApplicantName(request.getApplicantName());
        entity.setScore(request.getScore());
        entity.setTestMarks(request.getTestMarks());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setStatus(request.getStatus());
        entity.setIsPromoted(request.getIsPromoted());
        entity.setIsOffered(request.getIsOffered());
        entity.setRemarks(request.getRemarks());
        return AdmissionWaitingListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionWaitingListEntryResponse getById(Long entryId) {
        return AdmissionWaitingListEntryMapper.toResponse(repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionWaitingListEntry not found")));
    }

    @Override
    public Page<AdmissionWaitingListEntryResponse> getByWaitingListId(Long waitingListId, Pageable pageable) {
        List<AdmissionWaitingListEntry> all = repository.findByWaitingListId(waitingListId);
        List<AdmissionWaitingListEntry> paged = all.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new org.springframework.data.domain.PageImpl<>(paged, pageable, all.size())
                .map(AdmissionWaitingListEntryMapper::toResponse);
    }

    @Override
    public List<AdmissionWaitingListEntryResponse> getAllByWaitingListId(Long waitingListId) {
        return repository.findByWaitingListIdOrderByRankAsc(waitingListId).stream().map(AdmissionWaitingListEntryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AdmissionWaitingListEntryResponse updateStatus(Long entryId, String status) {
        AdmissionWaitingListEntry entity = repository.findById(entryId).orElseThrow(() -> new RuntimeException("AdmissionWaitingListEntry not found"));
        entity.setStatus(status);
        return AdmissionWaitingListEntryMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long entryId) {
        repository.deleteById(entryId);
    }
}
