package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionRegisterResponse;
import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionStatusResponse;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.PreAdmissionRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentProfile;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentProfileRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import jakarta.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreAdmissionRegistrationServiceImpl implements PreAdmissionRegistrationService {

    private final PreAdmissionRegistrationRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final ProgramRepository programRepository;
    private final AcademicSessionRepository academicSessionRepository;

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().toString();
    }

    @Override
    @Transactional
    public PreAdmissionRegisterResponse register(Map<String, String> fields, MultipartFile photo, MultipartFile signature) {
        String email = fields.get("email");
        if (repository.existsByEmail(email)) {
            throw new RuntimeException("An application with this email already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("A user account with this email already exists");
        }

        String registrationNumber = "REG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String trackingNumber = "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        PreAdmissionRegistration entity = new PreAdmissionRegistration();
        entity.setRegistrationNumber(registrationNumber);
        entity.setTrackingNumber(trackingNumber);
        entity.setFirstName(fields.getOrDefault("firstName", ""));
        entity.setLastName(fields.getOrDefault("lastName", ""));
        entity.setEmail(email);
        entity.setPhone(fields.get("phone"));
        entity.setGender(fields.get("gender"));
        entity.setBloodGroup(fields.get("bloodGroup"));
        entity.setAddress(fields.get("address"));
        entity.setFatherName(fields.get("fatherName"));
        entity.setMotherName(fields.get("motherName"));
        entity.setGuardianPhone(fields.get("guardianPhone"));
        entity.setProgramPreference1(fields.get("programPreference1"));
        entity.setProgramPreference2(fields.get("programPreference2"));
        entity.setProgramPreference3(fields.get("programPreference3"));
        if (fields.get("circularId") != null && !fields.get("circularId").isEmpty() && !"null".equalsIgnoreCase(fields.get("circularId"))) {
            try {
                entity.setCircularId(Long.parseLong(fields.get("circularId")));
            } catch (NumberFormatException ignored) {}
        }
        entity.setStatus("SUBMITTED");

        if (fields.get("dateOfBirth") != null && !fields.get("dateOfBirth").isEmpty()) {
            entity.setDateOfBirth(LocalDate.parse(fields.get("dateOfBirth")));
        }
        if (fields.get("sscGpa") != null && !fields.get("sscGpa").isEmpty()) {
            entity.setSscGpa(Double.parseDouble(fields.get("sscGpa")));
        }
        if (fields.get("sscYear") != null && !fields.get("sscYear").isEmpty()) {
            entity.setSscYear(Integer.parseInt(fields.get("sscYear")));
        }
        entity.setSscBoard(fields.get("sscBoard"));
        if (fields.get("hscGpa") != null && !fields.get("hscGpa").isEmpty()) {
            entity.setHscGpa(Double.parseDouble(fields.get("hscGpa")));
        }
        if (fields.get("hscYear") != null && !fields.get("hscYear").isEmpty()) {
            entity.setHscYear(Integer.parseInt(fields.get("hscYear")));
        }
        entity.setHscBoard(fields.get("hscBoard"));

        String applicantPassword = fields.get("password");
        if (applicantPassword == null || applicantPassword.length() < 6) {
            throw new RuntimeException("Password is required and must be at least 6 characters");
        }
        User user = new User();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(applicantPassword));
        user.setPhone(fields.get("phone"));
        user.setRole(UserRole.APPLICANT);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEmailVerified(false);
        User savedUser = userRepository.save(user);
        entity.setUser(savedUser);

        try {
            if (photo != null && !photo.isEmpty()) {
                String photoUrl = saveFile(photo, "photos");
                entity.setPhotoUrl(photoUrl);
            }
            if (signature != null && !signature.isEmpty()) {
                String signatureUrl = saveFile(signature, "signatures");
                entity.setSignatureUrl(signatureUrl);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save uploaded files", e);
        }

        PreAdmissionRegistration saved = repository.save(entity);

        PreAdmissionRegisterResponse response = new PreAdmissionRegisterResponse();
        response.setId(saved.getId());
        response.setRegistrationNumber(saved.getRegistrationNumber());
        response.setTrackingNumber(saved.getTrackingNumber());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setEmail(saved.getEmail());
        response.setPhone(saved.getPhone());
        response.setLoginEmail(email);
        response.setTempPassword(null);
        response.setPasswordProvided(true);
        response.setStatus(saved.getStatus());

        return response;
    }

    @Override
    public PreAdmissionStatusResponse checkStatus(String registrationNumber) {
        PreAdmissionRegistration entity = repository.findByRegistrationNumber(registrationNumber);
        if (entity == null) {
            throw new RuntimeException("Registration number not found");
        }
        PreAdmissionStatusResponse response = new PreAdmissionStatusResponse();
        response.setRegistrationNumber(entity.getRegistrationNumber());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setStatus(entity.getStatus());
        return response;
    }

    @Override
    public PreAdmissionRegistration getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    @Override
    public Page<PreAdmissionRegistration> getAll(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (search != null && !search.isEmpty()) {
            return repository.search(search, pageable);
        }
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public PreAdmissionRegistration update(Long id, PreAdmissionRegistration data) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        entity.setFirstName(data.getFirstName());
        entity.setLastName(data.getLastName());
        entity.setPhone(data.getPhone());
        entity.setGender(data.getGender());
        entity.setBloodGroup(data.getBloodGroup());
        entity.setAddress(data.getAddress());
        entity.setFatherName(data.getFatherName());
        entity.setMotherName(data.getMotherName());
        entity.setGuardianPhone(data.getGuardianPhone());
        entity.setSscGpa(data.getSscGpa());
        entity.setSscYear(data.getSscYear());
        entity.setSscBoard(data.getSscBoard());
        entity.setHscGpa(data.getHscGpa());
        entity.setHscYear(data.getHscYear());
        entity.setHscBoard(data.getHscBoard());
        entity.setProgramPreference1(data.getProgramPreference1());
        entity.setProgramPreference2(data.getProgramPreference2());
        entity.setProgramPreference3(data.getProgramPreference3());
        entity.setStatus(data.getStatus());
        entity.setRemarks(data.getRemarks());
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public PreAdmissionRegistration approve(Long id) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        entity.setStatus("APPROVED");
        PreAdmissionRegistration saved = repository.save(entity);

        // Auto-create/sync Student record
        try {
            String sId = entity.getRegistrationNumber() != null
                    ? entity.getRegistrationNumber().replace("REG-", "STU-")
                    : String.format("STU-%d-%03d", LocalDate.now().getYear(), entity.getId());

            Student student = studentRepository.findByStudentId(sId);
            if (student == null) {
                student = new Student();
                student.setStudentId(sId);
                String fullName = ((entity.getFirstName() != null ? entity.getFirstName() : "") + " " +
                        (entity.getLastName() != null ? entity.getLastName() : "")).trim();
                student.setFullName(!fullName.isEmpty() ? fullName : "Student " + entity.getId());
                student.setPhone(entity.getPhone());
                student.setAdmissionDate(LocalDate.now());

                if (entity.getEmail() != null) {
                    userRepository.findByEmail(entity.getEmail()).ifPresent(student::setUser);
                }

                List<Program> progs = programRepository.findAll();
                Program defaultProg = progs.isEmpty() ? null : progs.get(0);
                if (entity.getProgramPreference1() != null) {
                    String pref = entity.getProgramPreference1().toLowerCase().trim();
                    Program matched = progs.stream()
                            .filter(p -> p.getName() != null && (p.getName().toLowerCase().contains(pref) || pref.contains(p.getName().toLowerCase())))
                            .findFirst()
                            .orElse(defaultProg);
                    student.setProgram(matched);
                } else {
                    student.setProgram(defaultProg);
                }

                if (entity.getSessionId() != null) {
                    academicSessionRepository.findById(entity.getSessionId()).ifPresent(student::setAcademicSession);
                }
                if (student.getAcademicSession() == null) {
                    List<AcademicSession> sessions = academicSessionRepository.findAll();
                    if (!sessions.isEmpty()) student.setAcademicSession(sessions.get(0));
                }

                Student savedStudent = studentRepository.save(student);

                if (studentProfileRepository.findByStudentId(savedStudent.getId()).isEmpty()) {
                    StudentProfile profile = new StudentProfile();
                    profile.setStudentId(savedStudent.getId());
                    profile.setUniqueCode("PRF-" + sId);
                    profile.setAddress(entity.getAddress() != null ? entity.getAddress() : "Main Campus");
                    profile.setCity("Dhaka");
                    profile.setState("Dhaka");
                    profile.setZipCode("1200");
                    profile.setNationality("Bangladeshi");
                    profile.setBloodGroup(entity.getBloodGroup() != null ? entity.getBloodGroup() : "B+");
                    profile.setEmergencyContact(entity.getGuardianPhone() != null ? entity.getGuardianPhone() : entity.getPhone());
                    profile.setEmergencyContactName(entity.getFatherName() != null ? entity.getFatherName() : "Guardian");
                    profile.setMedicalInfo("None");
                    studentProfileRepository.save(profile);
                }
            }
        } catch (Exception e) {
            log.error("Error auto-creating student on pre-admission approval: {}", e.getMessage());
        }

        return saved;
    }

    @Override
    @Transactional
    public PreAdmissionRegistration reject(Long id, String remarks) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        entity.setStatus("REJECTED");
        entity.setRemarks(remarks);
        return repository.save(entity);
    }

    @Override
    @Transactional
    public Map<String, Object> processMerit() {
        List<PreAdmissionRegistration> approved = repository.findAll().stream()
                .filter(r -> "ADMIT_CARD_GENERATED".equals(r.getStatus()) || "SUBMITTED".equals(r.getStatus()))
                .toList();
        int count = 0;
        for (PreAdmissionRegistration reg : approved) {
            reg.setStatus("MERIT_PROCESSED");
            repository.save(reg);
            count++;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("processedCount", count);
        return result;
    }

    @Override
    public List<PreAdmissionRegistration> getMeritPreview() {
        return repository.findAll().stream()
                .filter(r -> "MERIT_PROCESSED".equals(r.getStatus()) || "ALLOCATED".equals(r.getStatus()))
                .sorted(Comparator.comparing(PreAdmissionRegistration::getSscGpa).reversed())
                .toList();
    }

    @Override
    public String getAdmitCard(Long id) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        return generateAdmitCardHtml(entity);
    }

    @Override
    public byte[] getAdmitCardPdf(Long id) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        return generatePdfBytes(entity);
    }

    @Override
    public byte[] getRegistrationPdf(String registrationNumber) {
        PreAdmissionRegistration entity = repository.findByRegistrationNumber(registrationNumber);
        if (entity == null) {
            throw new RuntimeException("Registration not found");
        }
        return generatePdfBytes(entity);
    }

    @Override
    public byte[] getRegistrationQrCode(String registrationNumber) {
        return generateQrCodePng(registrationNumber);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", repository.count());
        stats.put("submitted", repository.countByStatus("SUBMITTED"));
        stats.put("approved", repository.countByStatus("ADMIT_CARD_GENERATED"));
        stats.put("rejected", repository.countByStatus("REJECTED"));
        stats.put("meritProcessed", repository.countByStatus("MERIT_PROCESSED"));
        stats.put("allocated", repository.countByStatus("ALLOCATED"));
        stats.put("enrolled", repository.countByStatus("ENROLLED"));
        return stats;
    }

    @Override
    public PreAdmissionRegistration getByRegistrationNumber(String registrationNumber) {
        return repository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    @Transactional
    public void updatePaymentId(Long id, Long paymentId) {
        PreAdmissionRegistration entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        entity.setPaymentId(paymentId);
        repository.save(entity);
    }

    private String saveFile(MultipartFile file, String subDir) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String ext = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filename = UUID.randomUUID() + ext;
        Path uploadPath = Paths.get(uploadDir).resolve("pre-admissions").resolve(subDir);
        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(filename).toAbsolutePath();
        Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/pre-admissions/" + subDir + "/" + filename;
    }

    private String generateAdmitCardHtml(PreAdmissionRegistration entity) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    .card { border: 2px solid #004080; padding: 20px; max-width: 600px; margin: auto; }
                    .header { text-align: center; border-bottom: 2px solid #004080; padding-bottom: 10px; margin-bottom: 15px; }
                    .header h1 { margin: 0; color: #004080; font-size: 18px; }
                    .header h2 { margin: 5px 0 0; color: #333; font-size: 14px; }
                    .row { display: flex; margin: 8px 0; }
                    .label { font-weight: bold; width: 150px; color: #555; }
                    .value { flex: 1; color: #222; }
                    .photo { text-align: center; margin: 15px 0; }
                    .photo img { width: 120px; height: 150px; border: 1px solid #ccc; }
                    .footer { margin-top: 20px; border-top: 1px solid #ccc; padding-top: 10px; text-align: center; font-size: 12px; color: #666; }
                    @media print { body { margin: 0; } }
                </style>
            </head>
            <body>
                <div class="card">
                    <div class="header">
                        <h1>ELITE UNIVERSITY</h1>
                        <h2>ADMISSION TEST ADMIT CARD</h2>
                    </div>
                    <div class="row"><span class="label">Registration No:</span><span class="value">%s</span></div>
                    <div class="row"><span class="label">Name:</span><span class="value">%s %s</span></div>
                    <div class="row"><span class="label">Email:</span><span class="value">%s</span></div>
                    <div class="row"><span class="label">Phone:</span><span class="value">%s</span></div>
                    <div class="row"><span class="label">Date of Birth:</span><span class="value">%s</span></div>
                    <div class="row"><span class="label">Program Preference:</span><span class="value">%s</span></div>
                    <div class="row"><span class="label">Status:</span><span class="value">%s</span></div>
                    <div class="footer">This is a system-generated document. No signature required.</div>
                </div>
            </body>
            </html>
            """.formatted(
                entity.getRegistrationNumber(),
                entity.getFirstName(), entity.getLastName(),
                entity.getEmail(),
                entity.getPhone() != null ? entity.getPhone() : "N/A",
                entity.getDateOfBirth() != null ? entity.getDateOfBirth().toString() : "N/A",
                entity.getProgramPreference1() != null ? entity.getProgramPreference1() : "N/A",
                entity.getStatus()
        );
    }

    private byte[] generatePdfBytes(PreAdmissionRegistration entity) {
        String html = generateAdmitCardHtml(entity);
        return html.getBytes();
    }

    private byte[] generateQrCodePng(String registrationNumber) {
        String qrContent = "https://localhost:4200/pre-admission/status?reg=" + registrationNumber;
        int size = 200;
        int[][] matrix = new int[size][size];

        String binary = "";
        for (char c : qrContent.toCharArray()) {
            binary += String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
        }

        int mid = size / 2;
        for (int i = 0; i < Math.min(binary.length(), size * size); i++) {
            int row = i / size;
            int col = i % size;
            matrix[row][col] = binary.charAt(i) == '1' ? 1 : 0;
        }

        int[][] qrMatrix = new int[size][size];
        drawFinderPattern(qrMatrix, 0, 0);
        drawFinderPattern(qrMatrix, size - 7, 0);
        drawFinderPattern(qrMatrix, 0, size - 7);

        int idx = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (qrMatrix[row][col] == 0 && idx < binary.length()) {
                    qrMatrix[row][col] = binary.charAt(idx) == '1' ? 1 : 0;
                    idx++;
                }
            }
        }

        return generatePngFromMatrix(qrMatrix, size);
    }

    private void drawFinderPattern(int[][] matrix, int startRow, int startCol) {
        int size = matrix.length;
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                int row = startRow + r;
                int col = startCol + c;
                if (row >= 0 && row < size && col >= 0 && col < size) {
                    if (r == 0 || r == 6 || c == 0 || c == 6 || (r >= 2 && r <= 4 && c >= 2 && c <= 4)) {
                        matrix[row][col] = 1;
                    }
                }
            }
        }
    }

    private byte[] generatePngFromMatrix(int[][] matrix, int size) {
        int scale = 4;
        int imgSize = size * scale;
        int[] pixels = new int[imgSize * imgSize];

        for (int y = 0; y < imgSize; y++) {
            for (int x = 0; x < imgSize; x++) {
                int val = matrix[y / scale][x / scale] == 1 ? 0xFF000000 : 0xFFFFFFFF;
                pixels[y * imgSize + x] = val;
            }
        }

        try {
            java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(imgSize, imgSize, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            image.setRGB(0, 0, imgSize, imgSize, pixels, 0, imgSize);
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}
