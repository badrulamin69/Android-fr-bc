package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.FeeStructureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeStructureResponse;
import com.brilliantsofts.EliteUniversity.entity.FeeStructure;
import com.brilliantsofts.EliteUniversity.dto.mapper.FeeStructureMapper;
import com.brilliantsofts.EliteUniversity.repository.FeeStructureRepository;
import com.brilliantsofts.EliteUniversity.service.FeeStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeStructureServiceImpl implements FeeStructureService {
    @Autowired
    private FeeStructureRepository repository;

    @Override
    public FeeStructureResponse create(FeeStructureRequest request) {
        return FeeStructureMapper.toResponse(repository.save(FeeStructureMapper.toEntity(request)));
    }

    @Override
    public FeeStructureResponse update(Long id, FeeStructureRequest request) {
        FeeStructure entity = repository.findById(id).orElseThrow(() -> new RuntimeException("FeeStructure not found"));
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setProgramId(request.getProgramId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setAmount(request.getAmount());
        entity.setDueDays(request.getDueDays());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setDescription(request.getDescription());
        entity.setIsActive(request.getIsActive());
        return FeeStructureMapper.toResponse(repository.save(entity));
    }

    @Override
    public FeeStructureResponse getById(Long id) {
        return FeeStructureMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("FeeStructure not found")));
    }

    @Override
    public Page<FeeStructureResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(FeeStructureMapper::toResponse);
    }

    @Override
    public List<FeeStructureResponse> getBySemesterAndProgram(Long semesterId, Long programId) {
        return repository.findBySemesterIdAndProgramId(semesterId, programId).stream()
                .map(FeeStructureMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
