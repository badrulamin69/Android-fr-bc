package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.AssignmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.AssignmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AssignmentResponse;
import com.brilliantsofts.EliteUniversity.entity.Assignment;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Employee;
import com.brilliantsofts.EliteUniversity.entity.Subject;
import com.brilliantsofts.EliteUniversity.repository.AssignmentRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.SubjectRepository;
import com.brilliantsofts.EliteUniversity.repository.BatchRepository;
import com.brilliantsofts.EliteUniversity.service.AssignmentService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository repository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BatchRepository batchRepository;

    private void syncAssignmentsIfEmpty() {
        if (repository.count() == 0) {
            List<Course> courses = courseRepository.findAll();
            List<Subject> subjects = subjectRepository.findAll();
            Employee defaultEmp = employeeRepository.findAll().stream().findFirst().orElse(null);

            if (courses.isEmpty() || subjects.isEmpty()) return;

            String[][] samples = {
                { "Binary Search Trees & AVL Implementation", "Implement self-balancing BST and perform standard traversals.", "100" },
                { "Normalization & Relational Schema Design", "Design 3NF and BCNF schema for a real-world enterprise system.", "50" },
                { "Socket Programming & TCP Handshake", "Build client-server multi-threaded socket program in Java/Python.", "100" },
                { "Process Scheduling Simulation", "Simulate Round Robin, Shortest Job First and Priority scheduling algorithms.", "75" }
            };

            for (int i = 0; i < samples.length; i++) {
                String[] s = samples[i];
                Assignment a = new Assignment();
                a.setUniqueCode("ASG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                a.setTitle(s[0]);
                a.setDescription(s[1]);
                a.setMaxMarks(Integer.parseInt(s[2]));
                a.setDueDate(LocalDateTime.now().plusDays(14 + (i * 7)));
                a.setCourse(courses.get(i % courses.size()));
                a.setSubject(subjects.get(i % subjects.size()));
                if (defaultEmp != null) {
                    a.setAdministration(defaultEmp);
                }
                repository.save(a);
            }
        }
    }

    @Override
    public AssignmentResponse create(AssignmentRequest request) {
        Assignment entity = AssignmentMapper.toEntity(request);
        entity.setUniqueCode("ASG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) {
            entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        } else {
            employeeRepository.findAll().stream().findFirst().ifPresent(entity::setAdministration);
        }
        if (request.getSectionId() != null) entity.setSection(batchRepository.findById(request.getSectionId()).orElse(null));
        return AssignmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public AssignmentResponse update(Long id, AssignmentRequest request) {
        Assignment entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found"));
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDueDate(request.getDueDate());
        entity.setMaxMarks(request.getMaxMarks());
        if (request.getCourseId() != null) entity.setCourse(courseRepository.findById(request.getCourseId()).orElse(null));
        if (request.getSubjectId() != null) entity.setSubject(subjectRepository.findById(request.getSubjectId()).orElse(null));
        if (request.getAdministrationId() != null) entity.setAdministration(employeeRepository.findById(request.getAdministrationId()).orElse(null));
        if (request.getSectionId() != null) entity.setSection(batchRepository.findById(request.getSectionId()).orElse(null));
        return AssignmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public AssignmentResponse getById(Long id) {
        return AssignmentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found")));
    }

    @Override
    public Page<AssignmentResponse> getAll(Pageable pageable, String search) {
        syncAssignmentsIfEmpty();
        if (search != null && !search.isEmpty()) {
            Specification<Assignment> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(AssignmentMapper::toResponse);
        }
        return repository.findAll(pageable).map(AssignmentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
