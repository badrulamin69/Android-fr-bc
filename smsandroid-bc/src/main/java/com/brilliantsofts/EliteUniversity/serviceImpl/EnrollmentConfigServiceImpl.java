package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.EnrollmentConfigMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.EnrollmentConfig;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.EnrollmentConfigRepository;
import com.brilliantsofts.EliteUniversity.repository.SemesterRepository;
import com.brilliantsofts.EliteUniversity.service.EnrollmentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentConfigServiceImpl implements EnrollmentConfigService {
    @Autowired
    private EnrollmentConfigRepository repository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;

    @Override
    public EnrollmentConfigResponse create(EnrollmentConfigRequest request) {
        EnrollmentConfig entity = EnrollmentConfigMapper.toEntity(request);
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getAcademicSessionId() != null) entity.setAcademicSession(academicSessionRepository.findById(request.getAcademicSessionId()).orElse(null));
        return EnrollmentConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public EnrollmentConfigResponse update(Long id, EnrollmentConfigRequest request) {
        EnrollmentConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EnrollmentConfig not found"));
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setLateEnrollmentDate(request.getLateEnrollmentDate());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        entity.setEnrollmentStatus(request.getEnrollmentStatus());
        entity.setActive(request.isActive());
        entity.setRequiresAdvisorApproval(request.isRequiresAdvisorApproval());
        entity.setRequiresPayment(request.isRequiresPayment());
        entity.setAllowLateEnrollment(request.isAllowLateEnrollment());
        entity.setRemarks(request.getRemarks());
        if (request.getSemesterId() != null) entity.setSemester(semesterRepository.findById(request.getSemesterId()).orElse(null));
        if (request.getAcademicSessionId() != null) entity.setAcademicSession(academicSessionRepository.findById(request.getAcademicSessionId()).orElse(null));
        return EnrollmentConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public EnrollmentConfigResponse getById(Long id) {
        return EnrollmentConfigMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("EnrollmentConfig not found")));
    }

    @Override
    public List<EnrollmentConfigResponse> getAll() {
        return repository.findAll().stream().map(EnrollmentConfigMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentConfigResponse> getActive() {
        return repository.findByActiveTrue().stream().map(EnrollmentConfigMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public EnrollmentConfigResponse getBySemester(Long semesterId) {
        return EnrollmentConfigMapper.toResponse(repository.findBySemesterId(semesterId).orElseThrow(() -> new RuntimeException("EnrollmentConfig not found for semester")));
    }

    @Override
    public boolean isEnrollmentOpen(Long semesterId) {
        return repository.findBySemesterId(semesterId)
                .map(config -> config.isActive() && !config.isClosed())
                .orElse(false);
    }

    @Override
    public EnrollmentConfigResponse closeEnrollment(Long id) {
        EnrollmentConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EnrollmentConfig not found"));
        entity.setClosed(true);
        entity.setActive(false);
        return EnrollmentConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public EnrollmentConfigResponse reopenEnrollment(Long id) {
        EnrollmentConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EnrollmentConfig not found"));
        entity.setClosed(false);
        entity.setActive(true);
        return EnrollmentConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
