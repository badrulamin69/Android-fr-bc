package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.StudentAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentAttendanceResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentAttendance;
import com.brilliantsofts.EliteUniversity.dto.mapper.StudentAttendanceMapper;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentAttendanceRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.StudentAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;

    private void syncAttendanceIfEmpty() {
        if (repository.count() == 0) {
            List<Student> students = studentRepository.findAll();
            List<Course> courses = courseRepository.findAll();
            List<AcademicSession> sessions = academicSessionRepository.findAll();

            if (students.isEmpty() || courses.isEmpty()) return;

            AcademicSession defaultSession = sessions.isEmpty() ? null : sessions.get(0);
            LocalDate today = LocalDate.now();

            String[] statuses = {"PRESENT", "PRESENT", "PRESENT", "LATE", "PRESENT", "ABSENT"};
            int statusIdx = 0;

            for (Student s : students) {
                for (int dayOffset = 0; dayOffset < 3; dayOffset++) {
                    LocalDate attDate = today.minusDays(dayOffset);
                    for (int cIdx = 0; cIdx < Math.min(courses.size(), 2); cIdx++) {
                        Course c = courses.get(cIdx);
                        StudentAttendance att = new StudentAttendance();
                        att.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                        att.setStudent(s);
                        att.setCourse(c);
                        att.setSemester(s.getAcademicSession() != null ? s.getAcademicSession() : defaultSession);
                        att.setAttendanceDate(attDate);
                        String st = statuses[statusIdx++ % statuses.length];
                        att.setStatus(st);
                        att.setCheckInTime(attDate.atTime(9, (st.equals("LATE") ? 20 : 0)));
                        att.setRemarks(st.equals("PRESENT") ? "Attended full lecture" : (st.equals("LATE") ? "Joined 20m late" : "Absent"));
                        repository.save(att);
                    }
                }
            }
        }
    }

    @Override
    public StudentAttendanceResponse create(StudentAttendanceRequest request) {
        StudentAttendance entity = StudentAttendanceMapper.toEntity(request);
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId()).orElse(null);
            entity.setCourse(course);
        }
        if (request.getSemesterId() != null) {
            AcademicSession semester = academicSessionRepository.findById(request.getSemesterId()).orElse(null);
            entity.setSemester(semester);
        }
        if (entity.getAttendanceDate() == null) {
            entity.setAttendanceDate(LocalDate.now());
        }
        return StudentAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentAttendanceResponse update(Long id, StudentAttendanceRequest request) {
        StudentAttendance entity = repository.findById(id).orElseThrow(() -> new RuntimeException("StudentAttendance not found"));
        entity.setAttendanceDate(request.getAttendanceDate());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId()).orElse(null);
            entity.setStudent(student);
        }
        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId()).orElse(null);
            entity.setCourse(course);
        }
        if (request.getSemesterId() != null) {
            AcademicSession semester = academicSessionRepository.findById(request.getSemesterId()).orElse(null);
            entity.setSemester(semester);
        }
        return StudentAttendanceMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentAttendanceResponse getById(Long id) {
        return StudentAttendanceMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("StudentAttendance not found")));
    }

    @Override
    public Page<StudentAttendanceResponse> getAll(Pageable pageable, String search) {
        syncAttendanceIfEmpty();
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable).map(StudentAttendanceMapper::toResponse);
        }
        return repository.findAll(pageable).map(StudentAttendanceMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getStats() {
        syncAttendanceIfEmpty();
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("present", repository.countByStatus("PRESENT"));
        stats.put("absent", repository.countByStatus("ABSENT"));
        stats.put("late", repository.countByStatus("LATE"));
        return stats;
    }
}
