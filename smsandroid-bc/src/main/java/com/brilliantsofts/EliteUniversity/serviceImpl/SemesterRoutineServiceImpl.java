package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.SemesterRoutineMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SemesterRoutineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterRoutineResponse;
import com.brilliantsofts.EliteUniversity.entity.SemesterRoutine;
import com.brilliantsofts.EliteUniversity.repository.SemesterRoutineRepository;
import com.brilliantsofts.EliteUniversity.service.SemesterRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SemesterRoutineServiceImpl implements SemesterRoutineService {

    @Autowired
    private SemesterRoutineRepository repository;

    @Override
    public SemesterRoutineResponse create(SemesterRoutineRequest request) {
        SemesterRoutine entity = SemesterRoutineMapper.toEntity(request);
        entity.setUniqueCode("SR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return SemesterRoutineMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterRoutineResponse update(Long id, SemesterRoutineRequest request) {
        SemesterRoutine entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SemesterRoutine not found"));
        entity.setSemesterId(request.getSemesterId());
        entity.setProgramId(request.getProgramId());
        entity.setBatchId(request.getBatchId());
        entity.setDescription(request.getDescription());
        entity.setTotalWeeks(request.getTotalWeeks());
        entity.setMidtermWeek(request.getMidtermWeek());
        entity.setFinalExamWeek(request.getFinalExamWeek());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActive(request.isActive());
        return SemesterRoutineMapper.toResponse(repository.save(entity));
    }

    @Override
    public SemesterRoutineResponse getById(Long id) {
        return SemesterRoutineMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("SemesterRoutine not found")));
    }

    @Override
    public Page<SemesterRoutineResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(SemesterRoutineMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
