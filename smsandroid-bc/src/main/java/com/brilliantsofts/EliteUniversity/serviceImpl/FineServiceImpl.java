package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.FineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FineResponse;
import com.brilliantsofts.EliteUniversity.entity.Fine;
import com.brilliantsofts.EliteUniversity.dto.mapper.FineMapper;
import com.brilliantsofts.EliteUniversity.repository.FineRepository;
import com.brilliantsofts.EliteUniversity.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineServiceImpl implements FineService {
    @Autowired
    private FineRepository repository;

    @Override
    public FineResponse create(FineRequest request) {
        return FineMapper.toResponse(repository.save(FineMapper.toEntity(request)));
    }

    @Override
    public FineResponse update(Long id, FineRequest request) {
        Fine entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found"));
        entity.setStudentId(request.getStudentId());
        entity.setInvoiceId(request.getInvoiceId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setAmount(request.getAmount());
        entity.setReason(request.getReason());
        entity.setIssuedBy(request.getIssuedBy());
        entity.setStatus(request.getStatus());
        entity.setIssuedDate(request.getIssuedDate());
        return FineMapper.toResponse(repository.save(entity));
    }

    @Override
    public FineResponse getById(Long id) {
        return FineMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found")));
    }

    @Override
    public Page<FineResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(FineMapper::toResponse);
    }

    @Override
    public List<FineResponse> getByStudentId(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(FineMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public FineResponse waiveFine(Long id) {
        Fine entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Fine not found"));
        entity.setStatus("WAIVED");
        return FineMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
