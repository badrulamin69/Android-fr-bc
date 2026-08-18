package com.brilliantsofts.EliteUniversity.security;

import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.entity.Employee;
import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationResultRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("ownershipSecurity")
@RequiredArgsConstructor
public class OwnershipSecurityService {

    private final StudentRepository studentRepository;
    private final ApplicantRepository applicantRepository;
    private final EmployeeRepository employeeRepository;
    private final ExaminationResultRepository examinationResultRepository;

    /**
     * Verifies if the authenticated user is a SUPER_ADMIN or ADMIN, or is the owner student of the specified student ID.
     */
    public boolean isStudentOwner(Long studentId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (hasRole(authentication, UserRole.SUPER_ADMIN, UserRole.ADMIN)) {
            return true;
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long currentUserId = userDetails.getId();
            Student student = studentRepository.findById(studentId).orElse(null);
            if (student != null && student.getUser() != null) {
                return student.getUser().getId().equals(currentUserId);
            }
        }
        return false;
    }

    /**
     * Verifies if the authenticated user is a SUPER_ADMIN or ADMIN, or is the owner applicant of the specified applicant ID.
     */
    public boolean isApplicantOwner(Long applicantId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (hasRole(authentication, UserRole.SUPER_ADMIN, UserRole.ADMIN)) {
            return true;
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long currentUserId = userDetails.getId();
            Applicant applicant = applicantRepository.findById(applicantId).orElse(null);
            if (applicant != null && applicant.getUser() != null) {
                return applicant.getUser().getId().equals(currentUserId);
            }
        }
        return false;
    }

    /**
     * Verifies if the authenticated user is a SUPER_ADMIN or ADMIN, or is the owner user of the specified user ID.
     */
    public boolean isUserSelfOrAdmin(Long userId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (hasRole(authentication, UserRole.SUPER_ADMIN, UserRole.ADMIN)) {
            return true;
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getId().equals(userId);
        }
        return false;
    }

    /**
     * Verifies if the authenticated user owns the examination result (student role only).
     * Admins/Teachers always have access.
     */
    public boolean isExaminationResultOwner(Long examinationResultId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (hasRole(authentication, UserRole.SUPER_ADMIN, UserRole.ADMIN, UserRole.TEACHER)) {
            return true;
        }

        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Long currentUserId = userDetails.getId();
            ExaminationResult result = examinationResultRepository.findById(examinationResultId).orElse(null);
            if (result != null && result.getStudent() != null && result.getStudent().getUser() != null) {
                return result.getStudent().getUser().getId().equals(currentUserId);
            }
        }
        return false;
    }

    /**
     * Verifies if the authenticated user is a TEACHER or has admin access.
     */
    public boolean isTeacherOrAdmin(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        return hasRole(authentication, UserRole.SUPER_ADMIN, UserRole.ADMIN, UserRole.TEACHER);
    }

    private boolean hasRole(Authentication authentication, UserRole... roles) {
        for (UserRole role : roles) {
            String roleName = "ROLE_" + role.name();
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(roleName))) {
                return true;
            }
        }
        return false;
    }
}
