package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.TranscriptRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TranscriptResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.Transcript;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.TranscriptMapper;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.TranscriptRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TranscriptServiceImpl implements TranscriptService {
    @Autowired
    private TranscriptRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TranscriptResponse create(TranscriptRequest request) {
        Transcript entity = TranscriptMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId()).orElse(null);
            entity.setProgram(program);
        }
        if (request.getSemesterId() != null) {
            AcademicSession semester = academicSessionRepository.findById(request.getSemesterId()).orElse(null);
            entity.setSemester(semester);
        }
        if (request.getIssuedById() != null) {
            User issuedBy = userRepository.findById(request.getIssuedById()).orElse(null);
            entity.setIssuedBy(issuedBy);
        }
        return TranscriptMapper.toResponse(repository.save(entity));
    }

    @Override
    public TranscriptResponse update(Long id, TranscriptRequest request) {
        Transcript entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Transcript not found"));
        entity.setTranscriptNumber(request.getTranscriptNumber());
        entity.setStatus(request.getStatus());
        entity.setGpa(request.getGpa());
        entity.setTotalCredits(request.getTotalCredits());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId()).orElse(null);
            entity.setProgram(program);
        }
        if (request.getSemesterId() != null) {
            AcademicSession semester = academicSessionRepository.findById(request.getSemesterId()).orElse(null);
            entity.setSemester(semester);
        }
        if (request.getIssuedById() != null) {
            User issuedBy = userRepository.findById(request.getIssuedById()).orElse(null);
            entity.setIssuedBy(issuedBy);
        }
        return TranscriptMapper.toResponse(repository.save(entity));
    }

    @Override
    public TranscriptResponse getById(Long id) {
        return TranscriptMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Transcript not found")));
    }

    @Override
    public Page<TranscriptResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(TranscriptMapper::toResponse);
        }
        return repository.findAll(pageable).map(TranscriptMapper::toResponse);
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
        return stats;
    }
}
