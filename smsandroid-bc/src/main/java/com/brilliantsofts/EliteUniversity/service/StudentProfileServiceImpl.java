package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.StudentProfileMapper;
import com.brilliantsofts.EliteUniversity.dto.request.StudentProfileRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentProfileResponse;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentProfile;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentProfileRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;
    private final StudentRepository studentRepository;
    private final PreAdmissionRegistrationRepository preAdmissionRepo;

    private StudentProfileResponse enrichResponse(StudentProfile entity) {
        if (entity == null) return null;
        StudentProfileResponse res = StudentProfileMapper.toResponse(entity);
        if (entity.getStudentId() != null) {
            studentRepository.findById(entity.getStudentId()).ifPresent(student -> {
                res.setStudentName(student.getFullName());
                res.setStudentCode(student.getStudentId());
                res.setPhone(student.getPhone());
                if (student.getUser() != null) {
                    res.setEmail(student.getUser().getEmail());
                }
                if (student.getProgram() != null) {
                    res.setProgramName(student.getProgram().getName());
                    if (student.getProgram().getDepartment() != null) {
                        res.setDepartmentName(student.getProgram().getDepartment().getName());
                    }
                }
            });
        }
        return res;
    }

    private void syncProfilesIfEmpty() {
        if (repository.count() == 0) {
            List<Student> students = studentRepository.findAll();
            for (Student s : students) {
                if (s.getId() != null && repository.findByStudentId(s.getId()).isEmpty()) {
                    StudentProfile profile = new StudentProfile();
                    profile.setStudentId(s.getId());
                    profile.setUniqueCode("PRF-" + (s.getStudentId() != null ? s.getStudentId() : s.getId().toString()));
                    profile.setNationality("Bangladeshi");
                    profile.setCity("Dhaka");
                    profile.setState("Dhaka");
                    profile.setZipCode("1200");
                    profile.setBloodGroup("B+");
                    profile.setAddress("Main Campus, Elite University");
                    profile.setEmergencyContact(s.getPhone() != null ? s.getPhone() : "+8801700000000");
                    profile.setEmergencyContactName("Guardian");
                    profile.setMedicalInfo("None");
                    repository.save(profile);
                }
            }
        }
    }

    @Override
    public StudentProfileResponse create(StudentProfileRequest request) {
        StudentProfile entity = StudentProfileMapper.toEntity(request);
        if (entity.getUniqueCode() == null || entity.getUniqueCode().isBlank()) {
            entity.setUniqueCode("PRF-" + System.currentTimeMillis());
        }
        return enrichResponse(repository.save(entity));
    }

    @Override
    public StudentProfileResponse update(Long id, StudentProfileRequest request) {
        StudentProfile entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentProfile not found with id: " + id));
        entity.setStudentId(request.getStudentId());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setZipCode(request.getZipCode());
        entity.setNationality(request.getNationality());
        entity.setBloodGroup(request.getBloodGroup());
        entity.setEmergencyContact(request.getEmergencyContact());
        entity.setEmergencyContactName(request.getEmergencyContactName());
        entity.setMedicalInfo(request.getMedicalInfo());
        return enrichResponse(repository.save(entity));
    }

    @Override
    public StudentProfileResponse getById(Long id) {
        StudentProfile entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentProfile not found with id: " + id));
        return enrichResponse(entity);
    }

    @Override
    public Page<StudentProfileResponse> getAll(String search, Pageable pageable) {
        syncProfilesIfEmpty();
        return repository.findAllWithSearch(search, pageable)
                .map(this::enrichResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("StudentProfile not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
