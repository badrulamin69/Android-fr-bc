package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.StudentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentProfile;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.StudentMapper;
import com.brilliantsofts.EliteUniversity.repository.*;
import com.brilliantsofts.EliteUniversity.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private AcademicSessionRepository academicSessionRepository;
    @Autowired
    private PreAdmissionRegistrationRepository preAdmissionRepo;
    @Autowired
    private DepartmentAllocationRepository allocationRepo;
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    private void syncApprovedPreAdmissionStudents() {
        try {
            List<Program> progs = programRepository.findAll();
            List<AcademicSession> sessions = academicSessionRepository.findAll();
            Program defaultProg = progs.isEmpty() ? null : progs.get(0);
            AcademicSession defaultSession = sessions.isEmpty() ? null : sessions.get(0);
            int idx = 1;

            // 1. Sync from ALL PreAdmission registrations
            var preAdmissions = preAdmissionRepo.findAll();
            for (var pa : preAdmissions) {
                String sId = pa.getRegistrationNumber() != null
                        ? pa.getRegistrationNumber().replace("REG-", "STU-")
                        : String.format("STU-%d-%03d", LocalDate.now().getYear(), idx++);

                if (repository.findByStudentId(sId) == null) {
                    Student s = new Student();
                    s.setStudentId(sId);
                    String fullName = ((pa.getFirstName() != null ? pa.getFirstName() : "") + " " +
                            (pa.getLastName() != null ? pa.getLastName() : "")).trim();
                    s.setFullName(!fullName.isEmpty() ? fullName : "Student " + pa.getId());
                    s.setPhone(pa.getPhone());
                    s.setAdmissionDate(LocalDate.now());

                    if (pa.getEmail() != null) {
                        userRepository.findByEmail(pa.getEmail()).ifPresent(s::setUser);
                    }

                    if (pa.getProgramPreference1() != null) {
                        String pref = pa.getProgramPreference1().toLowerCase().trim();
                        Program matched = progs.stream()
                                .filter(p -> p.getName() != null && (p.getName().toLowerCase().contains(pref) || pref.contains(p.getName().toLowerCase())))
                                .findFirst()
                                .orElse(defaultProg);
                        s.setProgram(matched);
                    } else {
                        s.setProgram(defaultProg);
                    }

                    if (pa.getSessionId() != null) {
                        academicSessionRepository.findById(pa.getSessionId()).ifPresent(s::setAcademicSession);
                    }
                    if (s.getAcademicSession() == null) {
                        s.setAcademicSession(defaultSession);
                    }

                    Student saved = repository.save(s);

                    // Sync Profile
                    if (studentProfileRepository.findByStudentId(saved.getId()).isEmpty()) {
                        StudentProfile profile = new StudentProfile();
                        profile.setStudentId(saved.getId());
                        profile.setUniqueCode("PRF-" + sId);
                        profile.setAddress(pa.getAddress() != null ? pa.getAddress() : "Main Campus");
                        profile.setCity("Dhaka");
                        profile.setState("Dhaka");
                        profile.setZipCode("1200");
                        profile.setNationality("Bangladeshi");
                        profile.setBloodGroup(pa.getBloodGroup() != null ? pa.getBloodGroup() : "B+");
                        profile.setEmergencyContact(pa.getGuardianPhone() != null ? pa.getGuardianPhone() : pa.getPhone());
                        profile.setEmergencyContactName(pa.getFatherName() != null ? pa.getFatherName() : "Guardian");
                        profile.setMedicalInfo("None");
                        studentProfileRepository.save(profile);
                    }
                }
            }

            // 2. Sync from Department Allocations
            var allocations = allocationRepo.findAll();
            for (var alloc : allocations) {
                if (alloc.getRegistrationId() != null) {
                    var pa = preAdmissionRepo.findById(alloc.getRegistrationId()).orElse(null);
                    if (pa != null) {
                        String sId = pa.getRegistrationNumber() != null
                                ? pa.getRegistrationNumber().replace("REG-", "STU-")
                                : String.format("STU-%d-%03d", LocalDate.now().getYear(), idx++);
                        Student s = repository.findByStudentId(sId);
                        if (s == null) {
                            s = new Student();
                            s.setStudentId(sId);
                            String fullName = ((pa.getFirstName() != null ? pa.getFirstName() : "") + " " +
                                    (pa.getLastName() != null ? pa.getLastName() : "")).trim();
                            s.setFullName(!fullName.isEmpty() ? fullName : "Student " + pa.getId());
                            s.setPhone(pa.getPhone());
                            s.setAdmissionDate(LocalDate.now());

                            if (alloc.getAllocatedProgramId() != null) {
                                programRepository.findById(alloc.getAllocatedProgramId()).ifPresent(s::setProgram);
                            }
                            if (s.getProgram() == null) s.setProgram(defaultProg);
                            s.setAcademicSession(defaultSession);
                            repository.save(s);
                        } else if (alloc.getAllocatedProgramId() != null && s.getProgram() == null) {
                            programRepository.findById(alloc.getAllocatedProgramId()).ifPresent(s::setProgram);
                            repository.save(s);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error syncing pre-admission students: {}", e.getMessage());
        }
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        Student entity = StudentMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        if (request.getAcademicSessionId() != null) entity.setAcademicSession(academicSessionRepository.findById(request.getAcademicSessionId()).orElse(null));
        return StudentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        entity.setStudentId(request.getStudentId());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setAdmissionDate(request.getAdmissionDate());
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getApplicantId() != null) entity.setApplicant(applicantRepository.findById(request.getApplicantId()).orElse(null));
        if (request.getProgramId() != null) entity.setProgram(programRepository.findById(request.getProgramId()).orElse(null));
        if (request.getAcademicSessionId() != null) entity.setAcademicSession(academicSessionRepository.findById(request.getAcademicSessionId()).orElse(null));
        return StudentMapper.toResponse(repository.save(entity));
    }

    @Override
    public StudentResponse getById(Long id) {
        return StudentMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found")));
    }

    @Override
    public StudentResponse getByStudentId(String studentId) {
        return StudentMapper.toResponse(repository.findByStudentId(studentId));
    }

    @Override
    public StudentResponse getByUserId(Long userId) {
        return StudentMapper.toResponse(repository.findByUserId(userId));
    }

    @Override
    public StudentResponse getByApplicantId(Long applicantId) {
        return StudentMapper.toResponse(repository.findByApplicantId(applicantId));
    }

    @Override
    public Page<StudentResponse> getAll(Pageable pageable) {
        syncApprovedPreAdmissionStudents();
        return repository.findAll(pageable).map(StudentMapper::toResponse);
    }

    @Override
    public List<StudentResponse> getByProgram(Long programId) {
        return repository.findByProgramId(programId).stream().map(StudentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getBySession(Long sessionId) {
        return repository.findByAcademicSessionId(sessionId).stream().map(StudentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
