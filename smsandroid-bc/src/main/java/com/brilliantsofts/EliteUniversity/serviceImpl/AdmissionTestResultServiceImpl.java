package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionTestResultMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResultResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantTestResultView;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestResult;
import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestResultRepository;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdmissionTestResultServiceImpl implements AdmissionTestResultService {

    @Autowired
    private AdmissionTestResultRepository repository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private PreAdmissionRegistrationRepository preAdmissionRegistrationRepository;

    @Override
    public AdmissionTestResultResponse create(AdmissionTestResultRequest request) {
        AdmissionTestResult entity = AdmissionTestResultMapper.toEntity(request);
        return toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestResultResponse update(Long id, AdmissionTestResultRequest request) {
        AdmissionTestResult entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionTestResult not found"));
        entity.setWrittenMarks(request.getWrittenMarks());
        entity.setMcqMarks(request.getMcqMarks());
        entity.setVivaMarks(request.getVivaMarks());
        entity.setWrittenMax(request.getWrittenMax());
        entity.setMcqMax(request.getMcqMax());
        entity.setVivaMax(request.getVivaMax());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        return toResponse(repository.save(entity));
    }

    @Override
    public AdmissionTestResultResponse getById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AdmissionTestResult not found")));
    }

    @Override
    public Page<AdmissionTestResultResponse> getAll(Pageable pageable, String search) {
        if (search == null || search.trim().isEmpty()) {
            return enrich(repository.findAll(pageable));
        }
        String s = search.trim();
        List<Long> ids = applicantRepository
                .findByApplicationNumberContainingIgnoreCaseOrFullNameContainingIgnoreCase(s, s)
                .stream().map(Applicant::getId).collect(Collectors.toList());
        try {
            ids.add(Long.parseLong(s));
        } catch (NumberFormatException ignored) {
        }
        if (ids.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0);
        }
        return enrich(repository.findByRegistrationIdIn(ids, pageable));
    }

    @Override
    public Page<ApplicantTestResultView> getApplicantsWithResults(Pageable pageable, String search) {
        // BUG FIX: was querying empty Applicant table; now queries PreAdmissionRegistration which has actual data
        Page<PreAdmissionRegistration> registrations;
        if (search == null || search.trim().isEmpty()) {
            registrations = preAdmissionRegistrationRepository.findAll(pageable);
        } else {
            registrations = preAdmissionRegistrationRepository.search(search.trim(), pageable);
        }

        return registrations.map(reg -> {
            ApplicantTestResultView view = new ApplicantTestResultView();
            view.setApplicantId(reg.getId());
            view.setApplicantRoll(reg.getRegistrationNumber());
            view.setApplicantName(reg.getFirstName() + " " + reg.getLastName());
            view.setRegistrationId(reg.getId());
            repository.findByRegistrationId(reg.getId()).stream().findFirst().ifPresent(result -> {
                view.setResultId(result.getId());
                view.setWrittenMarks(result.getWrittenMarks());
                view.setMcqMarks(result.getMcqMarks());
                view.setVivaMarks(result.getVivaMarks());
                view.setWrittenMax(result.getWrittenMax());
                view.setMcqMax(result.getMcqMax());
                view.setVivaMax(result.getVivaMax());
                view.setTotalWeightedScore(result.getTotalWeightedScore());
                view.setStatus(result.getStatus());
                view.setRemarks(result.getRemarks());
                view.setTestId(result.getTestId());
            });
            return view;
        });
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AdmissionTestResultResponse> bulkSave(List<AdmissionTestResultRequest> requests) {
        List<AdmissionTestResult> entities = requests.stream()
                .map(AdmissionTestResultMapper::toEntity)
                .collect(Collectors.toList());
        List<AdmissionTestResult> saved = repository.saveAll(entities);
        return saved.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AdmissionTestResultResponse toResponse(AdmissionTestResult entity) {
        AdmissionTestResultResponse response = AdmissionTestResultMapper.toResponse(entity);
        if (entity.getRegistrationId() != null) {
            // Try PreAdmissionRegistration first, then fallback to Applicant table
            preAdmissionRegistrationRepository.findById(entity.getRegistrationId())
                .ifPresentOrElse(
                    reg -> response.setApplicantRoll(reg.getRegistrationNumber()),
                    () -> applicantRepository.findById(entity.getRegistrationId())
                            .ifPresent(a -> response.setApplicantRoll(a.getApplicationNumber()))
                );
        }
        return response;
    }

    private Page<AdmissionTestResultResponse> enrich(Page<AdmissionTestResult> page) {
        List<Long> regIds = page.getContent().stream()
                .map(AdmissionTestResult::getRegistrationId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Map<Long, String> rollMap = applicantRepository.findAllById(regIds).stream()
                .collect(Collectors.toMap(Applicant::getId, Applicant::getApplicationNumber, (a, b) -> a));
        return page.map(entity -> {
            AdmissionTestResultResponse response = AdmissionTestResultMapper.toResponse(entity);
            response.setApplicantRoll(rollMap.get(entity.getRegistrationId()));
            return response;
        });
    }
}
