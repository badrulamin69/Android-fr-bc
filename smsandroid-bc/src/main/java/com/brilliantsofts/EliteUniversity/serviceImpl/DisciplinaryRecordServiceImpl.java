package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.DisciplinaryRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DisciplinaryRecordResponse;
import com.brilliantsofts.EliteUniversity.entity.DisciplinaryRecord;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.DisciplinaryRecordMapper;
import com.brilliantsofts.EliteUniversity.repository.DisciplinaryRecordRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.DisciplinaryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DisciplinaryRecordServiceImpl implements DisciplinaryRecordService {
    @Autowired
    private DisciplinaryRecordRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DisciplinaryRecordResponse create(DisciplinaryRecordRequest request) {
        DisciplinaryRecord entity = DisciplinaryRecordMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getReportedById() != null) {
            User reportedBy = userRepository.findById(request.getReportedById()).orElse(null);
            entity.setReportedBy(reportedBy);
        }
        return DisciplinaryRecordMapper.toResponse(repository.save(entity));
    }

    @Override
    public DisciplinaryRecordResponse update(Long id, DisciplinaryRecordRequest request) {
        DisciplinaryRecord entity = repository.findById(id).orElseThrow(() -> new RuntimeException("DisciplinaryRecord not found"));
        entity.setIncidentDate(request.getIncidentDate());
        entity.setCategory(request.getCategory());
        entity.setSeverity(request.getSeverity());
        entity.setDescription(request.getDescription());
        entity.setActionTaken(request.getActionTaken());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getReportedById() != null) {
            User reportedBy = userRepository.findById(request.getReportedById()).orElse(null);
            entity.setReportedBy(reportedBy);
        }
        return DisciplinaryRecordMapper.toResponse(repository.save(entity));
    }

    @Override
    public DisciplinaryRecordResponse getById(Long id) {
        return DisciplinaryRecordMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("DisciplinaryRecord not found")));
    }

    @Override
    public Page<DisciplinaryRecordResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(DisciplinaryRecordMapper::toResponse);
        }
        return repository.findAll(pageable).map(DisciplinaryRecordMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("open", repository.countByStatus("OPEN"));
        stats.put("resolved", repository.countByStatus("RESOLVED"));
        stats.put("pending", repository.countByStatus("PENDING"));
        return stats;
    }
}
