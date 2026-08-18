package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionResultResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionResult;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionResultMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionResultRepository;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionResultServiceImpl implements AdmissionResultService {
    @Autowired
    private AdmissionResultRepository repository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public AdmissionResultResponse create(AdmissionResultRequest request) {
        AdmissionResult entity = AdmissionResultMapper.toEntity(request);
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return AdmissionResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public AdmissionResultResponse update(Long id, AdmissionResultRequest request) {
        AdmissionResult entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionResult not found"));
        entity.setAdmissionScore(request.getAdmissionScore());
        entity.setMeritPosition(request.getMeritPosition());
        entity.setResultStatus(request.getResultStatus());
        entity.setResultDate(request.getResultDate());
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return AdmissionResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public AdmissionResultResponse getById(Long id) {
        return AdmissionResultMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionResult not found")));
    }
    @Override
    public AdmissionResultResponse getByApplicant(Long applicantId) {
        return AdmissionResultMapper.toResponse(repository.findByApplicantId(applicantId));
    }
    @Override
    public List<AdmissionResultResponse> getAll() {
        return repository.findAll().stream().map(AdmissionResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<AdmissionResultResponse> getByProgram(Long programId) {
        return repository.findByProgramId(programId).stream().map(AdmissionResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<AdmissionResultResponse> getByStatus(String status) {
        return repository.findByResultStatus(status).stream().map(AdmissionResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<AdmissionResultResponse> getMeritList(Long programId) {
        return repository.findByProgramIdOrderByAdmissionScoreDesc(programId).stream().map(AdmissionResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
