package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.DiscountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DiscountResponse;
import com.brilliantsofts.EliteUniversity.entity.Discount;
import com.brilliantsofts.EliteUniversity.dto.mapper.DiscountMapper;
import com.brilliantsofts.EliteUniversity.repository.DiscountRepository;
import com.brilliantsofts.EliteUniversity.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository repository;

    @Override
    public DiscountResponse create(DiscountRequest request) {
        return DiscountMapper.toResponse(repository.save(DiscountMapper.toEntity(request)));
    }

    @Override
    public DiscountResponse update(Long id, DiscountRequest request) {
        Discount entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Discount not found"));
        entity.setStudentId(request.getStudentId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setDiscountType(request.getDiscountType());
        entity.setDiscountValue(request.getDiscountValue());
        entity.setDescription(request.getDescription());
        entity.setValidFrom(request.getValidFrom());
        entity.setValidTo(request.getValidTo());
        entity.setIsActive(request.getIsActive());
        return DiscountMapper.toResponse(repository.save(entity));
    }

    @Override
    public DiscountResponse getById(Long id) {
        return DiscountMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Discount not found")));
    }

    @Override
    public Page<DiscountResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(DiscountMapper::toResponse);
    }

    @Override
    public List<DiscountResponse> getByStudentId(Long studentId) {
        return repository.findByStudentId(studentId).stream().map(DiscountMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
