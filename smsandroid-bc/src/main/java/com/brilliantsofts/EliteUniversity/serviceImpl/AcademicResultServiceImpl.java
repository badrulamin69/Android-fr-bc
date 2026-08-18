package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicResultResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicResult;
import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;
import com.brilliantsofts.EliteUniversity.dto.mapper.AcademicResultMapper;
import com.brilliantsofts.EliteUniversity.repository.AcademicResultRepository;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.service.AcademicResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicResultServiceImpl implements AcademicResultService {
    @Autowired
    private AcademicResultRepository repository;
    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public AcademicResultResponse create(AcademicResultRequest request) {
        AcademicResult entity = AcademicResultMapper.toEntity(request);
        if (request.getApplicantId() != null) {
            Applicant applicant = applicantRepository.findById(request.getApplicantId()).orElse(null);
            entity.setApplicant(applicant);
        }
        return AcademicResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public AcademicResultResponse update(Long id, AcademicResultRequest request) {
        AcademicResult entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicResult not found"));
        entity.setExamType(request.getExamType());
        entity.setBoard(request.getBoard());
        entity.setInstitutionName(request.getInstitutionName());
        entity.setRollNumber(request.getRollNumber());
        entity.setRegistrationNumber(request.getRegistrationNumber());
        entity.setPassingYear(request.getPassingYear());
        entity.setGpa(request.getGpa());
        entity.setResultDocument(request.getResultDocument());
        if (request.getApplicantId() != null) {
            Applicant applicant = applicantRepository.findById(request.getApplicantId()).orElse(null);
            entity.setApplicant(applicant);
        }
        return AcademicResultMapper.toResponse(repository.save(entity));
    }
    @Override
    public AcademicResultResponse getById(Long id) {
        return AcademicResultMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AcademicResult not found")));
    }
    @Override
    public Page<AcademicResultResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AcademicResultMapper::toResponse);
    }
    @Override
    public List<AcademicResultResponse> getByApplicant(Long applicantId) {
        return repository.findByApplicantId(applicantId).stream().map(AcademicResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<AcademicResultResponse> getByExamType(AcademicExamType type) {
        return repository.findByExamType(type).stream().map(AcademicResultMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public AcademicResultResponse getApplicantExamResult(Long applicantId, AcademicExamType type) {
        return AcademicResultMapper.toResponse(repository.findByApplicantIdAndExamType(applicantId, type));
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
