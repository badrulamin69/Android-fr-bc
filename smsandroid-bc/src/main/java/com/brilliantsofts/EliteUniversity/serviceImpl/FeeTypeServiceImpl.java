package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.FeeTypeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeTypeResponse;
import com.brilliantsofts.EliteUniversity.entity.FeeType;
import com.brilliantsofts.EliteUniversity.dto.mapper.FeeTypeMapper;
import com.brilliantsofts.EliteUniversity.repository.FeeTypeRepository;
import com.brilliantsofts.EliteUniversity.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {
    @Autowired
    private FeeTypeRepository repository;

    @Override
    public FeeTypeResponse create(FeeTypeRequest request) {
        return FeeTypeMapper.toResponse(repository.save(FeeTypeMapper.toEntity(request)));
    }

    @Override
    public FeeTypeResponse update(Long id, FeeTypeRequest request) {
        FeeType entity = repository.findById(id).orElseThrow(() -> new RuntimeException("FeeType not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setDefaultAmount(request.getDefaultAmount());
        entity.setIsActive(request.getIsActive());
        return FeeTypeMapper.toResponse(repository.save(entity));
    }

    @Override
    public FeeTypeResponse getById(Long id) {
        return FeeTypeMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("FeeType not found")));
    }

    @Override
    public Page<FeeTypeResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(FeeTypeMapper::toResponse);
    }

    @Override
    public List<FeeTypeResponse> getActive() {
        return repository.findByIsActiveTrue().stream().map(FeeTypeMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<FeeTypeResponse> getByCategory(String category) {
        return repository.findByCategory(category).stream().map(FeeTypeMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
