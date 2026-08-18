package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.repository.AdmissionEnrollmentRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.service.StudentPortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentPortalServiceImpl implements StudentPortalService {

    private final PreAdmissionRegistrationRepository registrationRepository;
    private final AdmissionEnrollmentRepository enrollmentRepository;

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
}
