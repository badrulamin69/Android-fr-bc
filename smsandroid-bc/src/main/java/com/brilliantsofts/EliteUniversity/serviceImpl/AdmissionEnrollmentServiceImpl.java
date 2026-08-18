package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AdmissionEnrollmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AdmissionEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionEnrollment;
import com.brilliantsofts.EliteUniversity.repository.AdmissionEnrollmentRepository;
import com.brilliantsofts.EliteUniversity.service.AdmissionEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdmissionEnrollmentServiceImpl implements AdmissionEnrollmentService {

    private final AdmissionEnrollmentRepository repository;

    @Override
    @Transactional
    public AdmissionEnrollmentResponse create(AdmissionEnrollmentRequest request) {
        AdmissionEnrollment entity = AdmissionEnrollmentMapper.toEntity(request);
        return AdmissionEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AdmissionEnrollmentResponse update(Long id, AdmissionEnrollmentRequest request) {
        AdmissionEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission enrollment not found"));
        entity.setUniqueCode(request.getUniqueCode());
        entity.setEnrollmentNumber(request.getEnrollmentNumber());
        entity.setApplicationId(request.getApplicationId());
        entity.setStudentId(request.getStudentId());
        entity.setOfferLetterId(request.getOfferLetterId());
        entity.setProgramId(request.getProgramId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setSectionId(request.getSectionId());
        entity.setStatus(request.getStatus());
        entity.setEnrolledAt(request.getEnrolledAt());
        entity.setRemarks(request.getRemarks());
        entity.setIsDocumentVerified(request.getIsDocumentVerified());
        entity.setIsFeePaid(request.getIsFeePaid());
        entity.setTotalFeePaid(request.getTotalFeePaid());
        entity.setEnrolledById(request.getEnrolledById());
        return AdmissionEnrollmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public AdmissionEnrollmentResponse getById(Long id) {
        AdmissionEnrollment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission enrollment not found"));
        return AdmissionEnrollmentMapper.toResponse(entity);
    }

    @Override
    public Page<AdmissionEnrollmentResponse> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AdmissionEnrollmentMapper::toResponse);
        }
        return repository.findAll(pageable).map(AdmissionEnrollmentMapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("enrolled", repository.countByStatus("ENROLLED"));
        stats.put("pending", repository.countByStatus("PENDING"));
        stats.put("cancelled", repository.countByStatus("CANCELLED"));
        return stats;
    }
}
