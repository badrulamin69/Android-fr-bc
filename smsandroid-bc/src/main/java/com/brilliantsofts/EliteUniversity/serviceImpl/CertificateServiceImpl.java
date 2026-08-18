package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.CertificateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CertificateResponse;
import com.brilliantsofts.EliteUniversity.entity.Certificate;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.CertificateMapper;
import com.brilliantsofts.EliteUniversity.repository.CertificateRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CertificateResponse create(CertificateRequest request) {
        Certificate entity = CertificateMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getIssuedById() != null) {
            User issuedBy = userRepository.findById(request.getIssuedById()).orElse(null);
            entity.setIssuedBy(issuedBy);
        }
        return CertificateMapper.toResponse(repository.save(entity));
    }

    @Override
    public CertificateResponse update(Long id, CertificateRequest request) {
        Certificate entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found"));
        entity.setCertificateNumber(request.getCertificateNumber());
        entity.setCertificateType(request.getCertificateType());
        entity.setValidUntil(request.getValidUntil());
        entity.setStatus(request.getStatus());
        entity.setPurpose(request.getPurpose());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getIssuedById() != null) {
            User issuedBy = userRepository.findById(request.getIssuedById()).orElse(null);
            entity.setIssuedBy(issuedBy);
        }
        return CertificateMapper.toResponse(repository.save(entity));
    }

    @Override
    public CertificateResponse getById(Long id) {
        return CertificateMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Certificate not found")));
    }

    @Override
    public Page<CertificateResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(CertificateMapper::toResponse);
        }
        return repository.findAll(pageable).map(CertificateMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("issued", repository.countByStatus("ISSUED"));
        stats.put("pending", repository.countByStatus("PENDING"));
        stats.put("revoked", repository.countByStatus("REVOKED"));
        return stats;
    }
}
