package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.TeacherMapper;
import com.brilliantsofts.EliteUniversity.dto.request.TeacherRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherResponse;
import com.brilliantsofts.EliteUniversity.entity.Teacher;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.FacultyRepository;
import com.brilliantsofts.EliteUniversity.repository.TeacherRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    private TeacherResponse enrichResponse(Teacher entity) {
        if (entity == null) return null;
        TeacherResponse res = TeacherMapper.toResponse(entity);
        if (entity.getDepartmentId() != null) {
            departmentRepository.findById(entity.getDepartmentId()).ifPresent(d -> res.setDepartmentName(d.getName()));
        }
        if (entity.getFacultyId() != null) {
            facultyRepository.findById(entity.getFacultyId()).ifPresent(f -> res.setFacultyName(f.getName()));
        }
        if (entity.getUserId() != null) {
            userRepository.findById(entity.getUserId()).ifPresent(u -> {
                res.setUsername(u.getUsername());
                res.setRole(u.getRole() != null ? u.getRole().name() : "TEACHER");
            });
        } else {
            res.setRole("TEACHER");
        }
        return res;
    }

    @Override
    @Transactional
    public TeacherResponse create(TeacherRequest request) {
        Teacher entity = TeacherMapper.toEntity(request);
        entity.setUniqueCode("TCH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // Create or Link User account for role-based teacher login
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            String email = request.getEmail().trim();
            User user = userRepository.findByEmail(email).orElse(null);
            String rawPassword = (request.getPassword() != null && !request.getPassword().isBlank())
                    ? request.getPassword()
                    : "Teacher@123";

            UserRole targetRole = UserRole.TEACHER;
            if (request.getRole() != null && !request.getRole().isBlank()) {
                try {
                    targetRole = UserRole.valueOf(request.getRole().trim().toUpperCase());
                } catch (Exception ignored) {}
            }

            if (user == null) {
                user = new User();
                String username = (request.getUsername() != null && !request.getUsername().isBlank())
                        ? request.getUsername().trim()
                        : (request.getTeacherCode() != null && !request.getTeacherCode().isBlank()
                            ? request.getTeacherCode().trim()
                            : email);

                if (userRepository.existsByUsername(username)) {
                    username = email;
                }
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode(rawPassword));
                user.setPhone(request.getPhone());
                user.setRole(targetRole);
                user.setEnabled(true);
                user.setAccountNonLocked(true);
                user = userRepository.save(user);
            } else {
                if (request.getPassword() != null && !request.getPassword().isBlank()) {
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                }
                user.setRole(targetRole);
                user = userRepository.save(user);
            }
            entity.setUserId(user.getId());
        }

        Teacher saved = repository.save(entity);
        return enrichResponse(saved);
    }

    @Override
    @Transactional
    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        entity.setTeacherCode(request.getTeacherCode());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setGender(request.getGender());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setBloodGroup(request.getBloodGroup());
        entity.setNationalId(request.getNationalId());
        entity.setPassport(request.getPassport());
        entity.setNationality(request.getNationality());
        entity.setReligion(request.getReligion());
        entity.setMaritalStatus(request.getMaritalStatus());
        entity.setPhoto(request.getPhoto());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setEmergencyContact(request.getEmergencyContact());
        entity.setPresentAddress(request.getPresentAddress());
        entity.setPermanentAddress(request.getPermanentAddress());
        entity.setJoiningDate(request.getJoiningDate());
        entity.setEmploymentStatus(request.getEmploymentStatus());
        entity.setEmploymentType(request.getEmploymentType());
        entity.setDesignation(request.getDesignation());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setFacultyId(request.getFacultyId());
        entity.setOfficeRoom(request.getOfficeRoom());
        entity.setCampus(request.getCampus());
        entity.setHighestDegree(request.getHighestDegree());
        entity.setUniversity(request.getUniversity());
        entity.setSpecialization(request.getSpecialization());
        entity.setExperience(request.getExperience());
        entity.setCertifications(request.getCertifications());
        entity.setAssignedCourses(request.getAssignedCourses());
        entity.setSections(request.getSections());
        entity.setSemester(request.getSemester());
        entity.setCreditLoad(request.getCreditLoad());
        entity.setGoogleScholar(request.getGoogleScholar());
        entity.setOrcid(request.getOrcid());
        entity.setSalaryGrade(request.getSalaryGrade());
        entity.setBasicSalary(request.getBasicSalary());
        entity.setBankInformation(request.getBankInformation());
        entity.setTaxId(request.getTaxId());
        entity.setStatus(request.getStatus());

        // Update User credentials and role
        if (entity.getUserId() != null) {
            userRepository.findById(entity.getUserId()).ifPresent(user -> {
                if (request.getPassword() != null && !request.getPassword().isBlank()) {
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                }
                if (request.getRole() != null && !request.getRole().isBlank()) {
                    try {
                        user.setRole(UserRole.valueOf(request.getRole().trim().toUpperCase()));
                    } catch (Exception ignored) {}
                }
                if (request.getEmail() != null && !request.getEmail().isBlank()) {
                    user.setEmail(request.getEmail().trim());
                }
                if (request.getPhone() != null) {
                    user.setPhone(request.getPhone());
                }
                userRepository.save(user);
            });
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            User user = userRepository.findByEmail(request.getEmail().trim()).orElse(null);
            String rawPassword = (request.getPassword() != null && !request.getPassword().isBlank()) ? request.getPassword() : "Teacher@123";
            UserRole targetRole = UserRole.TEACHER;
            if (request.getRole() != null && !request.getRole().isBlank()) {
                try {
                    targetRole = UserRole.valueOf(request.getRole().trim().toUpperCase());
                } catch (Exception ignored) {}
            }
            if (user == null) {
                user = new User();
                user.setUsername(request.getTeacherCode() != null ? request.getTeacherCode().trim() : request.getEmail().trim());
                user.setEmail(request.getEmail().trim());
                user.setPassword(passwordEncoder.encode(rawPassword));
                user.setPhone(request.getPhone());
                user.setRole(targetRole);
                user.setEnabled(true);
                user.setAccountNonLocked(true);
                user = userRepository.save(user);
            }
            entity.setUserId(user.getId());
        }

        Teacher saved = repository.save(entity);
        return enrichResponse(saved);
    }

    @Override
    public TeacherResponse getById(Long id) {
        Teacher entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return enrichResponse(entity);
    }

    @Override
    public Page<TeacherResponse> getAll(String search, Long departmentId, Long facultyId, String designation, String status, Pageable pageable) {
        if (departmentId != null || facultyId != null || designation != null || status != null) {
            return repository.findAllFiltered(search, departmentId, facultyId, designation, status, pageable).map(this::enrichResponse);
        }
        return repository.findAllWithSearch(search, pageable).map(this::enrichResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, Object> getDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("totalTeachers", repository.count());
        return dashboard;
    }

    @Override
    public java.util.List<Map<String, Object>> getDocuments(Long teacherId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public Map<String, Object> addDocument(Long teacherId, Map<String, Object> document) {
        return document;
    }

    @Override
    public java.util.List<Map<String, Object>> getCourseAssignments(Long teacherId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<Map<String, Object>> getPublications(Long teacherId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<Map<String, Object>> getLeaves(Long teacherId) {
        return java.util.Collections.emptyList();
    }

    @Override
    public java.util.List<Map<String, Object>> getAttendance(Long teacherId) {
        return java.util.Collections.emptyList();
    }
}
