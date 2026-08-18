package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.repository.*;
import com.brilliantsofts.EliteUniversity.service.AcademicDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AcademicDashboardServiceImpl implements AcademicDashboardService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private CampusRepository campusRepository;

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalFaculties", facultyRepository.count());
        stats.put("totalDepartments", departmentRepository.count());
        stats.put("totalPrograms", programRepository.count());
        stats.put("totalCourses", courseRepository.count());
        stats.put("totalSubjects", subjectRepository.count());
        stats.put("activeSessions", academicSessionRepository.count());
        stats.put("activeSemesters", semesterRepository.count());
        stats.put("activeCampuses", campusRepository.count());
        return stats;
    }
}
