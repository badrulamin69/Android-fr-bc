package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.ResultMapper;
import com.brilliantsofts.EliteUniversity.dto.request.ResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ResultResponse;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Exam;
import com.brilliantsofts.EliteUniversity.entity.ExamResult;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.ExamRepository;
import com.brilliantsofts.EliteUniversity.repository.ExamResultRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.ResultService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ExamResultRepository repository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    private void syncResultsIfEmpty() {
        if (repository.count() == 0) {
            List<Student> students = studentRepository.findAll();
            List<Course> courses = courseRepository.findAll();
            List<Exam> exams = examRepository.findAll();

            if (students.isEmpty()) return;

            // If no exams exist, create default exams for existing courses
            if (exams.isEmpty() && !courses.isEmpty()) {
                for (Course c : courses) {
                    Exam ex = new Exam();
                    ex.setName("Final Examination - " + c.getCourseName());
                    ex.setCourse(c);
                    ex.setTotalMarks(100);
                    ex.setPassingMarks(40);
                    ex.setExamDate(LocalDateTime.now().minusDays(7));
                    ex.setExamType("FINAL");
                    ex.setDurationMinutes(180);
                    ex.setUniqueCode("EXM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    exams.add(examRepository.save(ex));
                }
            }

            if (exams.isEmpty()) return;

            BigDecimal[][] markOptions = {
                    { BigDecimal.valueOf(88), BigDecimal.valueOf(88.0), BigDecimal.valueOf(100), new BigDecimal("88.00") },
                    { BigDecimal.valueOf(79), BigDecimal.valueOf(79.0), BigDecimal.valueOf(100), new BigDecimal("79.00") },
                    { BigDecimal.valueOf(94), BigDecimal.valueOf(94.0), BigDecimal.valueOf(100), new BigDecimal("94.00") },
                    { BigDecimal.valueOf(73), BigDecimal.valueOf(73.0), BigDecimal.valueOf(100), new BigDecimal("73.00") },
                    { BigDecimal.valueOf(82), BigDecimal.valueOf(82.0), BigDecimal.valueOf(100), new BigDecimal("82.00") }
            };
            String[] grades = { "A+", "A", "A+", "B+", "A-" };

            int optIdx = 0;
            for (Student s : students) {
                for (int eIdx = 0; eIdx < Math.min(exams.size(), 3); eIdx++) {
                    Exam ex = exams.get(eIdx);
                    ExamResult res = new ExamResult();
                    res.setUniqueCode("RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    res.setStudent(s);
                    res.setExam(ex);
                    int cur = optIdx % markOptions.length;
                    res.setTotalMarksObtained(markOptions[cur][0]);
                    res.setPercentage(markOptions[cur][1]);
                    res.setTotalMarks(markOptions[cur][2]);
                    res.setGrade(grades[cur]);
                    res.setResultStatus("PASSED");
                    res.setRemarks("Cleared in First Attempt");
                    repository.save(res);
                    optIdx++;
                }
            }
        }
    }

    @Override
    public ResultResponse create(ResultRequest request) {
        ExamResult entity = ResultMapper.toEntity(request);
        entity.setUniqueCode("RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return ResultMapper.toResponse(repository.save(entity));
    }

    @Override
    public ResultResponse update(Long id, ResultRequest request) {
        ExamResult entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        entity.setTotalMarksObtained(request.getTotalMarksObtained());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPercentage(request.getPercentage());
        entity.setGrade(request.getGrade());
        entity.setResultStatus(request.getResultStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getExamId() != null) entity.setExam(examRepository.findById(request.getExamId()).orElse(null));
        if (request.getStudentId() != null) entity.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        return ResultMapper.toResponse(repository.save(entity));
    }

    @Override
    public ResultResponse getById(Long id) {
        return ResultMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Result not found")));
    }

    @Override
    public Page<ResultResponse> getAll(Pageable pageable, String search) {
        syncResultsIfEmpty();
        if (search != null && !search.isEmpty()) {
            Specification<ExamResult> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(cb.lower(root.get("grade")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("resultStatus")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("remarks")), "%" + search.toLowerCase() + "%"));
                predicates.add(cb.like(cb.lower(root.get("uniqueCode")), "%" + search.toLowerCase() + "%"));
                return cb.or(predicates.toArray(new Predicate[0]));
            };
            return repository.findAll(spec, pageable).map(ResultMapper::toResponse);
        }
        return repository.findAll(pageable).map(ResultMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
