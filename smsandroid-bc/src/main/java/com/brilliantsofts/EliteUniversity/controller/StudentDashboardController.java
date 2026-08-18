package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboards/student")
@RequiredArgsConstructor
public class StudentDashboardController {

    private final StudentRepository studentRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentFeeRepository studentFeeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getStudentDashboard(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalStudents", studentRepository.count());
        response.put("activeEnrollments", enrollmentRepository.count());
        response.put("registeredCourses", courseRegistrationRepository.count());
        response.put("pendingFees", studentFeeRepository.count());
        response.put("attendanceRate", 95.0);
        response.put("systemHealth", "UP");
        return ResponseEntity.ok(response);
    }
}
