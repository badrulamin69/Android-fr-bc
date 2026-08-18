package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.StudentPromotionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentPromotionResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentPromotion;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.StudentPromotionMapper;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentPromotionRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.StudentPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentPromotionServiceImpl implements StudentPromotionService {
    @Autowired
    private StudentPromotionRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentPromotionResponse create(StudentPromotionRequest request) {
        StudentPromotion entity = StudentPromotionMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getFromSemesterId() != null) {
            AcademicSession fromSemester = academicSessionRepository.findById(request.getFromSemesterId()).orElse(null);
            entity.setFromSemester(fromSemester);
        }
        if (request.getToSemesterId() != null) {
            AcademicSession toSemester = academicSessionRepository.findById(request.getToSemesterId()).orElse(null);
            entity.setToSemester(toSemester);
        }
        if (request.getApprovedById() != null) {
            User approvedBy = userRepository.findById(request.getApprovedById()).orElse(null);
            entity.setApprovedBy(approvedBy);
        }
        return StudentPromotionMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentPromotionResponse update(Long id, StudentPromotionRequest request) {
        StudentPromotion entity = repository.findById(id).orElseThrow(() -> new RuntimeException("StudentPromotion not found"));
        entity.setFromBatchId(request.getFromBatchId());
        entity.setToBatchId(request.getToBatchId());
        entity.setPromotionDate(request.getPromotionDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getFromSemesterId() != null) {
            AcademicSession fromSemester = academicSessionRepository.findById(request.getFromSemesterId()).orElse(null);
            entity.setFromSemester(fromSemester);
        }
        if (request.getToSemesterId() != null) {
            AcademicSession toSemester = academicSessionRepository.findById(request.getToSemesterId()).orElse(null);
            entity.setToSemester(toSemester);
        }
        if (request.getApprovedById() != null) {
            User approvedBy = userRepository.findById(request.getApprovedById()).orElse(null);
            entity.setApprovedBy(approvedBy);
        }
        return StudentPromotionMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentPromotionResponse getById(Long id) {
        return StudentPromotionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("StudentPromotion not found")));
    }

    @Override
    public Page<StudentPromotionResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(StudentPromotionMapper::toResponse);
        }
        return repository.findAll(pageable).map(StudentPromotionMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("promoted", repository.countByStatus("PROMOTED"));
        stats.put("pending", repository.countByStatus("PENDING"));
        stats.put("rejected", repository.countByStatus("REJECTED"));
        return stats;
    }
}
