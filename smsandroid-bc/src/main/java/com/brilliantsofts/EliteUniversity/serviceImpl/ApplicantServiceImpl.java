package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantResponse;
import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.enums.ApplicationLevel;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;
import com.brilliantsofts.EliteUniversity.dto.mapper.ApplicantMapper;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    private ApplicantRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public ApplicantResponse create(ApplicantRequest request) {
        Applicant entity = ApplicantMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return ApplicantMapper.toResponse(repository.save(entity));
    }
    @Override
    public ApplicantResponse update(Long id, ApplicantRequest request) {
        Applicant entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Applicant not found"));
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setApplicationLevel(request.getApplicationLevel());
        entity.setStatus(request.getStatus());
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        return ApplicantMapper.toResponse(repository.save(entity));
    }
    @Override
    public ApplicantResponse getById(Long id) {
        return ApplicantMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Applicant not found")));
    }
    @Override
    public ApplicantResponse getByApplicationNumber(String applicationNumber) {
        return ApplicantMapper.toResponse(repository.findByApplicationNumber(applicationNumber));
    }
    @Override
    public ApplicantResponse getByUserId(Long userId) {
        return ApplicantMapper.toResponse(repository.findByUserId(userId));
    }
    @Override
    public List<ApplicantResponse> getAll() {
        return repository.findAll().stream().map(ApplicantMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ApplicantResponse> getByLevel(ApplicationLevel level) {
        return repository.findByApplicationLevel(level).stream().map(ApplicantMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ApplicantResponse> getByStatus(ApplicationStatus status) {
        return repository.findByStatus(status).stream().map(ApplicantMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ApplicantResponse> getByProgram(Long programId) {
        return repository.findByProgramId(programId).stream().map(ApplicantMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
