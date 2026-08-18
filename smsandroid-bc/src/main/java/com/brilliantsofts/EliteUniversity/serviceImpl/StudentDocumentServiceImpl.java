package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.StudentDocumentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentDocumentResponse;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentDocument;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.StudentDocumentMapper;
import com.brilliantsofts.EliteUniversity.repository.StudentDocumentRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.StudentDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentDocumentServiceImpl implements StudentDocumentService {
    @Autowired
    private StudentDocumentRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentDocumentResponse create(StudentDocumentRequest request) {
        StudentDocument entity = StudentDocumentMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return StudentDocumentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentDocumentResponse update(Long id, StudentDocumentRequest request) {
        StudentDocument entity = repository.findById(id).orElseThrow(() -> new RuntimeException("StudentDocument not found"));
        entity.setDocumentType(request.getDocumentType());
        entity.setDocumentName(request.getDocumentName());
        entity.setFileUrl(request.getFileUrl());
        entity.setFileSize(request.getFileSize());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getVerifiedById() != null) {
            User verifiedBy = userRepository.findById(request.getVerifiedById()).orElse(null);
            entity.setVerifiedBy(verifiedBy);
            entity.setVerifiedAt(LocalDateTime.now());
        }
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        return StudentDocumentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentDocumentResponse getById(Long id) {
        return StudentDocumentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("StudentDocument not found")));
    }

    @Override
    public Page<StudentDocumentResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(StudentDocumentMapper::toResponse);
        }
        return repository.findAll(pageable).map(StudentDocumentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("verified", repository.countByStatus("VERIFIED"));
        stats.put("pending", repository.countByStatus("PENDING"));
        return stats;
    }
}
