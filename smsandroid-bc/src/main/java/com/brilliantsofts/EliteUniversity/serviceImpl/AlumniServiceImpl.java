package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AlumniRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AlumniResponse;
import com.brilliantsofts.EliteUniversity.entity.Alumni;
import com.brilliantsofts.EliteUniversity.entity.Department;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.dto.mapper.AlumniMapper;
import com.brilliantsofts.EliteUniversity.repository.AlumniRepository;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlumniServiceImpl implements AlumniService {
    @Autowired
    private AlumniRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public AlumniResponse create(AlumniRequest request) {
        Alumni entity = AlumniMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId()).orElse(null);
            entity.setProgram(program);
        }
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId()).orElse(null);
            entity.setDepartment(department);
        }
        return AlumniMapper.toResponse(repository.save(entity));
    }

    @Override
    public AlumniResponse update(Long id, AlumniRequest request) {
        Alumni entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Alumni not found"));
        entity.setGraduationDate(request.getGraduationDate());
        entity.setDegree(request.getDegree());
        entity.setCurrentCompany(request.getCurrentCompany());
        entity.setCurrentDesignation(request.getCurrentDesignation());
        entity.setCurrentLocation(request.getCurrentLocation());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setLinkedInProfile(request.getLinkedInProfile());
        entity.setIsAvailableForMentoring(request.getIsAvailableForMentoring());
        entity.setIsAvailableForRecruitment(request.getIsAvailableForRecruitment());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId()).orElse(null);
            entity.setProgram(program);
        }
        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId()).orElse(null);
            entity.setDepartment(department);
        }
        return AlumniMapper.toResponse(repository.save(entity));
    }

    @Override
    public AlumniResponse getById(Long id) {
        return AlumniMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Alumni not found")));
    }

    @Override
    public Page<AlumniResponse> getAll(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(AlumniMapper::toResponse);
        }
        return repository.findAll(pageable).map(AlumniMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        return stats;
    }
}
