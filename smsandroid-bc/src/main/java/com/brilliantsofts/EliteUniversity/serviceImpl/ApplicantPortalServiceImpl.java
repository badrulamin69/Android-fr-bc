package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.entity.*;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCircular;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.AdmissionEnrollmentRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListEntryRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestQuestionRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestResultRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionWaitingListEntryRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmitCardRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.SeatAllocationRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.security.JwtService;
import com.brilliantsofts.EliteUniversity.service.ApplicantPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApplicantPortalServiceImpl implements ApplicantPortalService {
    @Autowired
    private PreAdmissionRegistrationRepository registrationRepository;
    @Autowired
    private AdmissionTestRepository testRepository;
    @Autowired
    private AdmissionTestQuestionRepository questionRepository;
    @Autowired
    private AdmissionTestResultRepository testResultRepository;
    @Autowired
    private SeatAllocationRepository seatAllocationRepository;
    @Autowired
    private AdmissionEnrollmentRepository enrollmentRepository;
    @Autowired
    private AdmitCardRepository admitCardRepository;
    @Autowired
    private AdmissionMeritListEntryRepository meritListEntryRepository;
    @Autowired
    private AdmissionWaitingListEntryRepository waitingListEntryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.AdmissionCircularRepository circularRepository;
    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository sessionRepository;
    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.ProgramRepository programRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Object getMyRegistration(Long userId) {
        User user = userId != null ? userRepository.findById(userId).orElse(null) : null;
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> (r.getUser() != null && r.getUser().getId().equals(userId)) ||
                             (user != null && r.getEmail() != null && r.getEmail().equalsIgnoreCase(user.getEmail())) ||
                             (user != null && r.getRegistrationNumber() != null && r.getRegistrationNumber().equalsIgnoreCase(user.getUsername())))
                .findFirst()
                .orElse(null);

        if (registration == null) return null;

        Map<String, Object> map = new HashMap<>();
        map.put("id", registration.getId());
        map.put("registrationNumber", registration.getRegistrationNumber());
        map.put("trackingNumber", registration.getTrackingNumber());
        map.put("firstName", registration.getFirstName());
        map.put("lastName", registration.getLastName());
        map.put("email", registration.getEmail());
        map.put("phone", registration.getPhone());
        map.put("dateOfBirth", registration.getDateOfBirth());
        map.put("gender", registration.getGender());
        map.put("bloodGroup", registration.getBloodGroup());
        map.put("address", registration.getAddress());
        map.put("fatherName", registration.getFatherName());
        map.put("motherName", registration.getMotherName());
        map.put("guardianPhone", registration.getGuardianPhone());
        map.put("photoUrl", registration.getPhotoUrl());
        map.put("signatureUrl", registration.getSignatureUrl());
        map.put("sscGpa", registration.getSscGpa());
        map.put("sscYear", registration.getSscYear());
        map.put("sscBoard", registration.getSscBoard());
        map.put("hscGpa", registration.getHscGpa());
        map.put("hscYear", registration.getHscYear());
        map.put("hscBoard", registration.getHscBoard());
        map.put("status", registration.getStatus());
        map.put("remarks", registration.getRemarks());
        map.put("emailVerified", registration.isEmailVerified());
        map.put("sessionId", registration.getSessionId());
        map.put("circularId", registration.getCircularId());
        map.put("paymentId", registration.getPaymentId());
        map.put("createdAt", registration.getCreatedAt());
        map.put("updatedAt", registration.getUpdatedAt());

        // Circular Details
        AdmissionCircular circular = null;
        if (registration.getCircularId() != null) {
            circular = circularRepository.findById(registration.getCircularId()).orElse(null);
        }
        if (circular == null) {
            circular = circularRepository.findAll().stream().findFirst().orElse(null);
        }
        if (circular != null) {
            map.put("circularId", circular.getId());
            map.put("circularTitle", circular.getTitle() != null ? circular.getTitle() : "Undergraduate Admission Circular");
            map.put("circularCode", circular.getUniqueCode() != null ? circular.getUniqueCode() : "CIR-" + circular.getId());
            map.put("circularEndDate", circular.getValidUntil());
            map.put("circularStatus", circular.getStatus());
        } else {
            map.put("circularTitle", "Undergraduate Admission Circular 2025-2026");
            map.put("circularCode", "CIR-2025-ADM");
        }

        // Session Details
        if (registration.getSessionId() != null) {
            sessionRepository.findById(registration.getSessionId()).ifPresent(s -> {
                map.put("sessionName", s.getSessionName());
                map.put("academicYear", s.getStartDate() != null
                        ? String.valueOf(s.getStartDate().getYear())
                        : s.getSessionName());
            });
        }
        if (!map.containsKey("sessionName")) {
            map.put("sessionName", "2025-2026 Academic Session");
        }

        // Program Preferences
        String pref1 = registration.getProgramPreference1() != null ? registration.getProgramPreference1() : "B.Sc. in Computer Science & Engineering";
        map.put("programPreference1", pref1);
        map.put("programPreference2", registration.getProgramPreference2());
        map.put("programPreference3", registration.getProgramPreference3());
        map.put("appliedProgram", pref1);

        return map;
    }

    @Override
    public Object getMyTest(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return testRepository.findAll().stream()
                .filter(t -> t.getStatus() != null && t.getStatus().equalsIgnoreCase("ACTIVE"))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object getTestQuestions(Long testId) {
        return questionRepository.findAll().stream()
                .filter(q -> q.getTestId() != null && q.getTestId().equals(testId) && Boolean.TRUE.equals(q.getIsActive()))
                .map(q -> {
                    Map<String, Object> questionMap = new HashMap<>();
                    questionMap.put("id", q.getId());
                    questionMap.put("questionText", q.getQuestionText());
                    questionMap.put("optionA", q.getOptionA());
                    questionMap.put("optionB", q.getOptionB());
                    questionMap.put("optionC", q.getOptionC());
                    questionMap.put("optionD", q.getOptionD());
                    questionMap.put("optionE", q.getOptionE());
                    questionMap.put("marks", q.getMarks());
                    questionMap.put("subject", q.getSubject());
                    return questionMap;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Object startTest(Long userId, Long testId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return Map.of("error", "Registration not found");
        AdmissionTestResult attempt = new AdmissionTestResult();
        attempt.setTestId(testId);
        attempt.setRegistrationId(registration.getId());
        attempt.setStatus("IN_PROGRESS");
        return testResultRepository.save(attempt);
    }

    @Override
    public Object submitTest(Long userId, Long attemptId, Map<String, String> answers) {
        AdmissionTestResult attempt = testResultRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
        attempt.setStatus("COMPLETED");
        return testResultRepository.save(attempt);
    }

    @Override
    public Object getMyResults(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return Collections.emptyList();
        return testResultRepository.findAll().stream()
                .filter(r -> r.getRegistrationId() != null && r.getRegistrationId().equals(registration.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getMyAllocation(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return seatAllocationRepository.findAll().stream()
                .filter(a -> a.getRegistrationId() != null && a.getRegistrationId().equals(registration.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object confirmAllocation(Long userId, Long allocationId) {
        SeatAllocation allocation = seatAllocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
        allocation.setStatus("CONFIRMED");
        return seatAllocationRepository.save(allocation);
    }

    @Override
    public Object declineAllocation(Long userId, Long allocationId) {
        SeatAllocation allocation = seatAllocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
        allocation.setStatus("DECLINED");
        return seatAllocationRepository.save(allocation);
    }

    @Override
    public Object enrollSelf(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return Map.of("error", "Registration not found");
        AdmissionEnrollment enrollment = new AdmissionEnrollment();
        enrollment.setApplicationId(registration.getId());
        enrollment.setStatus("ENROLLED");
        enrollment.setUniqueCode(java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Object getMyEnrollment(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return enrollmentRepository.findByApplicationId(registration.getId()).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public Object admitAsStudent(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return Map.of("error", "Registration not found");

        boolean passed = testResultRepository.findAll().stream()
                .anyMatch(r -> r.getRegistrationId() != null
                        && r.getRegistrationId().equals(registration.getId())
                        && "PASSED".equalsIgnoreCase(r.getStatus()));
        if (!passed) return Map.of("error", "You have not passed the admission test");

        AdmissionEnrollment existing = enrollmentRepository.findByApplicationId(registration.getId()).stream()
                .findFirst()
                .orElse(null);
        if (existing != null) return existing;

        AdmissionEnrollment enrollment = new AdmissionEnrollment();
        enrollment.setApplicationId(registration.getId());
        enrollment.setStatus("ENROLLED");
        enrollment.setUniqueCode(java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        AdmissionEnrollment saved = enrollmentRepository.save(enrollment);

        // Migrate user role to STUDENT
        return migrateToStudentAndIssueToken(userId, saved);
    }

    @Override
    @Transactional
    public Object enrollWithChoice(Long userId, Long facultyId, Long departmentId, Long programId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return Map.of("error", "Registration not found");

        boolean passed = testResultRepository.findAll().stream()
                .anyMatch(r -> r.getRegistrationId() != null
                        && r.getRegistrationId().equals(registration.getId())
                        && "PASSED".equalsIgnoreCase(r.getStatus()));
        if (!passed) return Map.of("error", "You have not passed the admission test");

        // Check if already enrolled
        AdmissionEnrollment existing = enrollmentRepository.findByApplicationId(registration.getId()).stream()
                .findFirst()
                .orElse(null);

        AdmissionEnrollment enrollment;
        if (existing != null) {
            enrollment = existing;
        } else {
            enrollment = new AdmissionEnrollment();
            enrollment.setApplicationId(registration.getId());
            enrollment.setStatus("ENROLLED");
            enrollment.setUniqueCode(java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            if (programId != null) enrollment.setProgramId(programId);
            if (departmentId != null) enrollment.setDepartmentId(departmentId);
            if (facultyId != null) enrollment.setFacultyId(facultyId);
            enrollment.setEnrollmentNumber("ENR-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            enrollment = enrollmentRepository.save(enrollment);
        }

        return migrateToStudentAndIssueToken(userId, enrollment);
    }

    private Map<String, Object> migrateToStudentAndIssueToken(Long userId, AdmissionEnrollment enrollment) {
        User user = userRepository.findById(userId).orElse(null);
        String newToken = null;
        if (user != null && user.getRole() != UserRole.STUDENT) {
            user.setRole(UserRole.STUDENT);
            user = userRepository.save(user);
            // Issue a new JWT with STUDENT role
            try {
                var userDetails = userDetailsService.loadUserByUsername(user.getUsername());
                newToken = jwtService.generateToken(userDetails, user);
            } catch (Exception ignored) {}
        }

        Map<String, Object> result = new HashMap<>();
        result.put("enrollment", enrollment);
        result.put("roleChanged", true);
        result.put("newRole", "STUDENT");
        if (newToken != null) {
            result.put("token", newToken);
        }
        return result;
    }

    @Autowired
    private com.brilliantsofts.EliteUniversity.service.PreAdmissionRegistrationService preAdmissionRegistrationService;

    @Override
    public byte[] getAdmitCardPdf(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return preAdmissionRegistrationService.getAdmitCardPdf(registration.getId());
    }

    @Override
    public String getAdmitCardHtml(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return preAdmissionRegistrationService.getAdmitCard(registration.getId());
    }

    @Override
    public Object getMyMerit(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return meritListEntryRepository.findAll().stream()
                .filter(e -> e.getRegistrationId() != null && e.getRegistrationId().equals(registration.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Object getMyWaitingPosition(Long userId) {
        PreAdmissionRegistration registration = registrationRepository.findAll().stream()
                .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);
        if (registration == null) return null;
        return waitingListEntryRepository.findAll().stream()
                .filter(e -> e.getRegistrationId() != null && e.getRegistrationId().equals(registration.getId()))
                .findFirst()
                .orElse(null);
    }
}
