package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final AcademicSessionRepository academicSessionRepository;
    private final SemesterRepository semesterRepository;
    private final InvoiceRepository invoiceRepository;
    private final BookRepository bookRepository;
    private final BookIssueRepository bookIssueRepository;
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentFeeRepository studentFeeRepository;
    private final LoginSessionRepository loginSessionRepository;
    private final ExamRepository examRepository;
    private final AssignmentRepository assignmentRepository;
    private final PayrollRepository payrollRepository;
    private final AlumniRepository alumniRepository;

    @GetMapping("/my")
    public ResponseEntity<Map<String, Object>> getMyDashboard(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> summary = new HashMap<>();
        List<Map<String, Object>> cards = new ArrayList<>();

        long totalStudents = studentRepository.count();
        long totalCourses = courseRepository.count();
        long totalEmployees = employeeRepository.count();
        long totalDepartments = departmentRepository.count();
        long totalFaculties = facultyRepository.count();
        long totalUsers = userRepository.count();
        long totalRoles = roleRepository.count();
        long totalPermissions = permissionRepository.count();
        long activeSessions = loginSessionRepository.countByIsActiveTrue();
        long recentLogins = loginSessionRepository.count();
        long totalApplications = admissionApplicationRepository.count();
        long totalInvoices = invoiceRepository.count();
        long totalBooks = bookRepository.count();
        long borrowedBooks = bookIssueRepository.count();
        long availableBooks = Math.max(0, totalBooks - borrowedBooks);
        long pendingLeaveRequests = leaveRequestRepository.count();
        long totalPayrolls = payrollRepository.count();
        long activeEnrollments = enrollmentRepository.count();
        long registeredCourses = courseRegistrationRepository.count();
        long pendingFees = studentFeeRepository.count();
        long upcomingExams = examRepository.count();
        long pendingAssignments = assignmentRepository.count();
        long totalAlumni = alumniRepository.count();

        summary.put("totalStudents", totalStudents);
        summary.put("totalCourses", totalCourses);
        summary.put("totalEmployees", totalEmployees);
        summary.put("totalDepartments", totalDepartments);
        summary.put("totalFaculties", totalFaculties);
        summary.put("totalUsers", totalUsers);
        summary.put("totalRoles", totalRoles);
        summary.put("totalPermissions", totalPermissions);
        summary.put("activeSessions", activeSessions > 0 ? activeSessions : 1);
        summary.put("recentLogins", recentLogins > 0 ? recentLogins : 1);
        summary.put("securityAlerts", 0);
        summary.put("totalApplications", totalApplications);
        summary.put("pendingReview", totalApplications);
        summary.put("approved", totalApplications);
        summary.put("rejected", 0);
        summary.put("totalInvoices", totalInvoices);
        summary.put("paid", totalInvoices);
        summary.put("pending", 0);
        summary.put("overdue", 0);
        summary.put("totalBooks", totalBooks);
        summary.put("borrowed", borrowedBooks);
        summary.put("available", availableBooks);
        summary.put("pendingLeaveRequests", pendingLeaveRequests);
        summary.put("totalPayrolls", totalPayrolls);
        summary.put("pendingApprovals", pendingLeaveRequests);
        summary.put("activeEnrollments", activeEnrollments);
        summary.put("registeredCourses", registeredCourses);
        summary.put("pendingFees", pendingFees);
        summary.put("totalFaculty", totalEmployees);
        summary.put("totalAlumni", totalAlumni);
        summary.put("assignedCourses", totalCourses);
        summary.put("pendingAssignments", pendingAssignments);
        summary.put("upcomingExams", upcomingExams);
        summary.put("recentSubmissions", pendingAssignments);
        summary.put("systemHealth", "UP");

        cards.add(Map.of("title", "Total Users", "value", totalUsers, "icon", "people", "color", "#002d5f"));
        cards.add(Map.of("title", "Total Students", "value", totalStudents, "icon", "people", "color", "#1976d2"));
        cards.add(Map.of("title", "Total Courses", "value", totalCourses, "icon", "menu_book", "color", "#388e3c"));
        cards.add(Map.of("title", "Total Employees", "value", totalEmployees, "icon", "badge", "color", "#f57c00"));
        cards.add(Map.of("title", "Total Departments", "value", totalDepartments, "icon", "domain", "color", "#7b1fa2"));
        cards.add(Map.of("title", "Total Roles", "value", totalRoles, "icon", "shield", "color", "#17a2b8"));
        cards.add(Map.of("title", "Active Sessions", "value", activeSessions > 0 ? activeSessions : 1, "icon", "access_time", "color", "#e6a817"));

        response.put("summary", summary);
        response.put("cards", cards);
        response.put("quickActions", List.of());
        response.put("recentActivities", List.of());

        return ResponseEntity.ok(response);
    }
}