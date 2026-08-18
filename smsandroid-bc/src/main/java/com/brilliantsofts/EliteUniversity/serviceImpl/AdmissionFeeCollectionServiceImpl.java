package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionFeeCollectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionFeeCollectionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionFeeCollection;
import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionFeeCollectionMapper;
import com.brilliantsofts.EliteUniversity.repository.AdmissionFeeCollectionRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionFeeCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AdmissionFeeCollectionServiceImpl implements AdmissionFeeCollectionService {
    @Autowired
    private AdmissionFeeCollectionRepository repository;

    @Override
    public AdmissionFeeCollectionResponse create(AdmissionFeeCollectionRequest request) {
        AdmissionFeeCollection entity = AdmissionFeeCollectionMapper.toEntity(request);
        if (entity.getUniqueCode() == null) {
            entity.setUniqueCode("FEE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        return AdmissionFeeCollectionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionFeeCollectionResponse update(Long id, AdmissionFeeCollectionRequest request) {
        AdmissionFeeCollection entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionFeeCollection not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setCandidateId(request.getCandidateId());
        entity.setAmount(request.getAmount());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setTransactionId(request.getTransactionId());
        entity.setStatus(request.getStatus());
        entity.setReceivedBy(request.getReceivedBy());
        entity.setRemarks(request.getRemarks());
        return AdmissionFeeCollectionMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionFeeCollectionResponse getById(Long id) {
        return AdmissionFeeCollectionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AdmissionFeeCollection not found")));
    }

    @Override
    public Page<AdmissionFeeCollectionResponse> getAll(Pageable pageable, String search, String status) {
        return repository.findAll(pageable).map(AdmissionFeeCollectionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
