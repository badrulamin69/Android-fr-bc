package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.RegistrationConfigMapper;
import com.brilliantsofts.EliteUniversity.dto.request.RegistrationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RegistrationConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.RegistrationConfig;
import com.brilliantsofts.EliteUniversity.repository.RegistrationConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationConfigServiceImpl implements RegistrationConfigService {

    private final RegistrationConfigRepository repository;

    @Override
    public RegistrationConfigResponse create(RegistrationConfigRequest request) {
        RegistrationConfig entity = RegistrationConfigMapper.toEntity(request);
        entity.setStatus("OPEN");
        return RegistrationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public RegistrationConfigResponse update(Long id, RegistrationConfigRequest request) {
        RegistrationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistrationConfig not found with id: " + id));
        entity.setSemesterId(request.getSemesterId());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setMinCredits(request.getMinCredits());
        entity.setMaxCredits(request.getMaxCredits());
        entity.setAllowAddDrop(request.isAllowAddDrop());
        entity.setAddDropDeadline(request.getAddDropDeadline());
        entity.setAdvisorApprovalRequired(request.isAdvisorApprovalRequired());
        entity.setPaymentRequired(request.isPaymentRequired());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        return RegistrationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public RegistrationConfigResponse getById(Long id) {
        RegistrationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistrationConfig not found with id: " + id));
        return RegistrationConfigMapper.toResponse(entity);
    }

    @Override
    public List<RegistrationConfigResponse> getAll() {
        return repository.findAll().stream()
                .map(RegistrationConfigMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationConfigResponse> getActive() {
        return repository.findByIsActiveTrue().stream()
                .map(RegistrationConfigMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationConfigResponse getBySemester(Long semesterId) {
        RegistrationConfig entity = repository.findBySemesterId(semesterId)
                .orElseThrow(() -> new RuntimeException("RegistrationConfig not found for semester: " + semesterId));
        return RegistrationConfigMapper.toResponse(entity);
    }

    @Override
    public RegistrationConfigResponse closeRegistration(Long id) {
        RegistrationConfig entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistrationConfig not found with id: " + id));
        entity.setClosed(true);
        entity.setActive(false);
        entity.setStatus("CLOSED");
        return RegistrationConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("RegistrationConfig not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
