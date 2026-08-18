package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TeacherPromotionMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TeacherPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherPromotionResponse;
import com.brilliantsofts.EliteUniversity.entity.TeacherPromotion;
import com.brilliantsofts.EliteUniversity.repository.TeacherPromotionRepository;
import com.brilliantsofts.EliteUniversity.service.TeacherPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherPromotionServiceImpl implements TeacherPromotionService {

    @Autowired
    private TeacherPromotionRepository repository;

    @Override
    public TeacherPromotionResponse create(TeacherPromotionRequest request) {
        TeacherPromotion entity = TeacherPromotionMapper.toEntity(request);
        entity.setUniqueCode("PRM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return TeacherPromotionMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherPromotionResponse update(Long id, TeacherPromotionRequest request) {
        TeacherPromotion entity = repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherPromotion not found"));
        entity.setTeacherId(request.getTeacherId());
        entity.setPreviousDesignation(request.getPreviousDesignation());
        entity.setNewDesignation(request.getNewDesignation());
        entity.setPreviousDepartment(request.getPreviousDepartment());
        entity.setNewDepartment(request.getNewDepartment());
        entity.setPreviousSalaryGrade(request.getPreviousSalaryGrade());
        entity.setNewSalaryGrade(request.getNewSalaryGrade());
        entity.setPreviousSalary(request.getPreviousSalary());
        entity.setNewSalary(request.getNewSalary());
        entity.setPromotionDate(request.getPromotionDate());
        entity.setReason(request.getReason());
        entity.setApprovedBy(request.getApprovedBy());
        entity.setApprovedByName(request.getApprovedByName());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return TeacherPromotionMapper.toResponse(repository.save(entity));
    }

    @Override
    public TeacherPromotionResponse getById(Long id) {
        return TeacherPromotionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("TeacherPromotion not found")));
    }

    @Override
    public Page<TeacherPromotionResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable).map(TeacherPromotionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
