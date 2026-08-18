package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ExamCenterMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ExamCenterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ExamCenterResponse;
import com.brilliantsofts.EliteUniversity.entity.ExamCenter;
import com.brilliantsofts.EliteUniversity.repository.ExamCenterRepository;
import com.brilliantsofts.EliteUniversity.service.ExamCenterService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamCenterServiceImpl implements ExamCenterService {
    @Autowired
    private ExamCenterRepository repository;

    @Override
    public ExamCenterResponse create(ExamCenterRequest request) {
        ExamCenter entity = ExamCenterMapper.toEntity(request);
        return ExamCenterMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamCenterResponse update(Long id, ExamCenterRequest request) {
        ExamCenter entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ExamCenter not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setTotalCapacity(request.getTotalCapacity());
        entity.setContactPerson(request.getContactPerson());
        entity.setContactPhone(request.getContactPhone());
        entity.setActive(request.isActive());
        return ExamCenterMapper.toResponse(repository.save(entity));
    }

    @Override
    public ExamCenterResponse getById(Long id) {
        return ExamCenterMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ExamCenter not found")));
    }

    @Override
    public Page<ExamCenterResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            Specification<ExamCenter> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("code")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("city")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("address")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(ExamCenterMapper::toResponse);
        }
        return repository.findAll(pageable).map(ExamCenterMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
