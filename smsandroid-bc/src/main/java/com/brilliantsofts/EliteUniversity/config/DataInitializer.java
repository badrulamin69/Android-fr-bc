package com.brilliantsofts.EliteUniversity.config;

import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.entity.Faculty;
import com.brilliantsofts.EliteUniversity.entity.Department;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.entity.Employee;
import com.brilliantsofts.EliteUniversity.entity.Notice;
import com.brilliantsofts.EliteUniversity.entity.Menu;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCircular;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTest;
import com.brilliantsofts.EliteUniversity.entity.AdmissionApplication;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritList;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritListEntry;
import com.brilliantsofts.EliteUniversity.entity.SeatAllocation;
import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Examination;
import com.brilliantsofts.EliteUniversity.entity.GradeRule;
import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;
import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.repository.FacultyRepository;
import com.brilliantsofts.EliteUniversity.repository.DepartmentRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.repository.EmployeeRepository;
import com.brilliantsofts.EliteUniversity.repository.NoticeRepository;
import com.brilliantsofts.EliteUniversity.repository.MenuRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionCircularRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionApplicationRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionMeritListEntryRepository;
import com.brilliantsofts.EliteUniversity.repository.SeatAllocationRepository;
import com.brilliantsofts.EliteUniversity.repository.AcademicSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.repository.CourseRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationRepository;
import com.brilliantsofts.EliteUniversity.repository.GradeRuleRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final ProgramRepository programRepository;
    private final EmployeeRepository employeeRepository;
    private final NoticeRepository noticeRepository;
    private final MenuRepository menuRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdmissionCircularRepository admissionCircularRepository;
    private final PreAdmissionRegistrationRepository preAdmissionRegistrationRepository;
    private final AdmissionTestRepository admissionTestRepository;
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionMeritListRepository admissionMeritListRepository;
    private final AdmissionMeritListEntryRepository admissionMeritListEntryRepository;
    private final SeatAllocationRepository seatAllocationRepository;
    private final AcademicSessionRepository academicSessionRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ExaminationRepository examinationRepository;
    private final GradeRuleRepository gradeRuleRepository;
    private final ExaminationResultRepository examinationResultRepository;
    private final com.brilliantsofts.EliteUniversity.repository.ChoiceFillingConfigRepository choiceFillingConfigRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // 1. Seed SUPER_ADMIN
        User superAdmin = userRepository.findByUsername("superadmin").orElse(null);
        if (superAdmin == null) {
            superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("admin@eliteuniversity.edu");
            superAdmin.setPassword(passwordEncoder.encode("Admin@123456"));
            superAdmin.setPhone("+1234567890");
            superAdmin.setEnabled(true);
            superAdmin.setAccountNonLocked(true);
            superAdmin.setAccountNonExpired(true);
            superAdmin.setCredentialsNonExpired(true);
            superAdmin.setRole(UserRole.SUPER_ADMIN);
            superAdmin = userRepository.save(superAdmin);
            log.info("Default SUPER_ADMIN user created with username 'superadmin'");
        }

        // 2. Seed Menus
        seedMenus();
        ensureMissingMenus();
        ensureChoiceFillingConfig();

        // 3. Seed PreAdmissions, Tests, Merit Lists and Allocations
        seedPreAdmissionsAndApplications();

        // 3. Seed Faculties, Departments, and Programs
        if (facultyRepository.count() == 0) {
            log.info("Seeding Faculties, Departments, and Programs...");

            // Faculty of Science & Engineering
            Faculty fse = new Faculty();
            fse.setName("Faculty of Science & Engineering");
            fse.setCode("FSE");
            fse.setDescription("Nurturing innovation and scientific discovery through world-class labs and academic research.");
            fse = facultyRepository.save(fse);

            // Departments for FSE
            Department cse = new Department();
            cse.setName("Computer Science & Engineering");
            cse.setCode("CSE");
            cse.setFaculty(fse);
            cse = departmentRepository.save(cse);

            Department eee = new Department();
            eee.setName("Electrical & Electronic Engineering");
            eee.setCode("EEE");
            eee.setFaculty(fse);
            eee = departmentRepository.save(eee);

            // Programs for CSE & EEE
            Program bscCse = new Program();
            bscCse.setName("Bachelor of Science in Computer Science & Engineering");
            bscCse.setCode("BSc-CSE");
            bscCse.setDurationYears(4);
            bscCse.setTotalCredits(148);
            bscCse.setDepartment(cse);
            programRepository.save(bscCse);

            Program mscCse = new Program();
            mscCse.setName("Master of Science in Computer Science & Engineering");
            mscCse.setCode("MSc-CSE");
            mscCse.setDurationYears(2);
            mscCse.setTotalCredits(36);
            mscCse.setDepartment(cse);
            programRepository.save(mscCse);

            Program bscEee = new Program();
            bscEee.setName("Bachelor of Science in Electrical & Electronic Engineering");
            bscEee.setCode("BSc-EEE");
            bscEee.setDurationYears(4);
            bscEee.setTotalCredits(150);
            bscEee.setDepartment(eee);
            programRepository.save(bscEee);

            // Faculty of Business Administration
            Faculty fba = new Faculty();
            fba.setName("Faculty of Business Administration");
            fba.setCode("FBA");
            fba.setDescription("Developing next-generation business leaders and entrepreneurs using case studies and analytical training.");
            fba = facultyRepository.save(fba);

            // Departments for FBA
            Department fin = new Department();
            fin.setName("Finance & Banking");
            fin.setCode("FIN");
            fin.setFaculty(fba);
            fin = departmentRepository.save(fin);

            Department mkt = new Department();
            mkt.setName("Marketing");
            mkt.setCode("MKT");
            mkt.setFaculty(fba);
            mkt = departmentRepository.save(mkt);

            // Programs for Business
            Program bbaFin = new Program();
            bbaFin.setName("Bachelor of Business Administration in Finance");
            bbaFin.setCode("BBA-FIN");
            bbaFin.setDurationYears(4);
            bbaFin.setTotalCredits(124);
            bbaFin.setDepartment(fin);
            programRepository.save(bbaFin);

            Program mba = new Program();
            mba.setName("Master of Business Administration");
            mba.setCode("MBA");
            mba.setDurationYears(2);
            mba.setTotalCredits(60);
            mba.setDepartment(fin);
            programRepository.save(mba);
        }

        // 4. Seed Teachers/Professors
        if (employeeRepository.count() == 0) {
            log.info("Seeding Employees (Professors)...");

            Department cse = departmentRepository.findByCode("CSE").orElse(null);
            Department eee = departmentRepository.findByCode("EEE").orElse(null);
            Department fin = departmentRepository.findByCode("FIN").orElse(null);

            if (cse != null) {
                // Professor 1 (CSE)
                User user1 = new User();
                user1.setUsername("prof.badrul");
                user1.setEmail("badrul@eliteuniversity.edu");
                user1.setPassword(passwordEncoder.encode("Password@123"));
                user1.setPhone("+8801700000001");
                user1.setEnabled(true);
                user1.setAccountNonLocked(true);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setRole(UserRole.TEACHER);
                user1 = userRepository.save(user1);

                Employee e1 = new Employee();
                e1.setEmployeeId("EMP-CSE-001");
                e1.setFullName("Prof. Dr. Badrul Amin");
                e1.setPhone("+8801700000001");
                e1.setDesignation("Professor & Department Head");
                e1.setEmployeeType(EmployeeType.TEACHER);
                e1.setUser(user1);
                e1.setDepartment(cse);
                employeeRepository.save(e1);

                // Professor 2 (CSE)
                User user2 = new User();
                user2.setUsername("prof.sarah");
                user2.setEmail("sarah@eliteuniversity.edu");
                user2.setPassword(passwordEncoder.encode("Password@123"));
                user2.setPhone("+8801700000002");
                user2.setEnabled(true);
                user2.setAccountNonLocked(true);
                user2.setAccountNonExpired(true);
                user2.setCredentialsNonExpired(true);
                user2.setRole(UserRole.TEACHER);
                user2 = userRepository.save(user2);

                Employee e2 = new Employee();
                e2.setEmployeeId("EMP-CSE-002");
                e2.setFullName("Dr. Sarah Connor");
                e2.setPhone("+8801700000002");
                e2.setDesignation("Associate Professor");
                e2.setEmployeeType(EmployeeType.TEACHER);
                e2.setUser(user2);
                e2.setDepartment(cse);
                employeeRepository.save(e2);
            }

            if (eee != null) {
                // Professor 3 (EEE)
                User user3 = new User();
                user3.setUsername("prof.edison");
                user3.setEmail("edison@eliteuniversity.edu");
                user3.setPassword(passwordEncoder.encode("Password@123"));
                user3.setPhone("+8801700000003");
                user3.setEnabled(true);
                user3.setAccountNonLocked(true);
                user3.setAccountNonExpired(true);
                user3.setCredentialsNonExpired(true);
                user3.setRole(UserRole.TEACHER);
                user3 = userRepository.save(user3);

                Employee e3 = new Employee();
                e3.setEmployeeId("EMP-EEE-001");
                e3.setFullName("Prof. Dr. Thomas Edison");
                e3.setPhone("+8801700000003");
                e3.setDesignation("Professor");
                e3.setEmployeeType(EmployeeType.TEACHER);
                e3.setUser(user3);
                e3.setDepartment(eee);
                employeeRepository.save(e3);
            }

            if (fin != null) {
                // Professor 4 (Finance)
                User user4 = new User();
                user4.setUsername("prof.smith");
                user4.setEmail("smith@eliteuniversity.edu");
                user4.setPassword(passwordEncoder.encode("Password@123"));
                user4.setPhone("+8801700000004");
                user4.setEnabled(true);
                user4.setAccountNonLocked(true);
                user4.setAccountNonExpired(true);
                user4.setCredentialsNonExpired(true);
                user4.setRole(UserRole.TEACHER);
                user4 = userRepository.save(user4);

                Employee e4 = new Employee();
                e4.setEmployeeId("EMP-FIN-001");
                e4.setFullName("Dr. Adam Smith");
                e4.setPhone("+8801700000004");
                e4.setDesignation("Professor & Dean of Business");
                e4.setEmployeeType(EmployeeType.TEACHER);
                e4.setUser(user4);
                e4.setDepartment(fin);
                employeeRepository.save(e4);
            }
        }

        // 5. Seed Notices
        if (noticeRepository.count() == 0) {
            log.info("Seeding Notices...");

            Notice n1 = new Notice();
            n1.setTitle("Undergraduate Admission Circular - Fall 2026");
            n1.setContent("Elite University invites applications for admission into undergraduate programs (B.Sc. in CSE, B.Sc. in EEE, BBA) for the Fall 2026 semester. Candidates should apply online via the Admissions portal. The application deadline is September 15, 2026. Entrance examinations will be conducted on September 20, 2026.");
            n1.setPublishDate(LocalDateTime.now());
            n1.setExpiryDate(LocalDateTime.now().plusMonths(2));
            n1.setPublished(true);
            n1.setAudience(NoticeAudience.APPLICANT);
            n1.setCreatedBy(superAdmin);
            noticeRepository.save(n1);

            Notice n2 = new Notice();
            n2.setTitle("Graduate Program Admission Circular - Fall 2026");
            n2.setContent("Applications are open for graduate programs (M.Sc. in CSE, MBA) for the Fall 2026 session. Please submit your application with letters of recommendation and transcript copies. The written evaluation is scheduled for September 22, 2026.");
            n2.setPublishDate(LocalDateTime.now());
            n2.setExpiryDate(LocalDateTime.now().plusMonths(2));
            n2.setPublished(true);
            n2.setAudience(NoticeAudience.APPLICANT);
            n2.setCreatedBy(superAdmin);
            noticeRepository.save(n2);

            Notice n3 = new Notice();
            n3.setTitle("Welcome Ceremony & Orientation for Session 2026");
            n3.setContent("We are thrilled to welcome all newly admitted students to the Elite University campus. The orientation program will be held on September 28, 2026, at the Central Auditorium. Attendance is mandatory for all freshmen.");
            n3.setPublishDate(LocalDateTime.now());
            n3.setExpiryDate(LocalDateTime.now().plusMonths(1));
            n3.setPublished(true);
            n3.setAudience(NoticeAudience.ALL);
            n3.setCreatedBy(superAdmin);
            noticeRepository.save(n3);

            Notice n4 = new Notice();
            n4.setTitle("Research Grant Proposals Call for 2026");
            n4.setContent("Faculty members and research scholars are invited to submit their research proposals for the annual university research grants. The submission portal is open until October 15, 2026. Focus areas include AI, Green Tech, and Financial Policy.");
            n4.setPublishDate(LocalDateTime.now());
            n4.setExpiryDate(LocalDateTime.now().plusMonths(2));
            n4.setPublished(true);
            n4.setAudience(NoticeAudience.TEACHER);
            n4.setCreatedBy(superAdmin);
            noticeRepository.save(n4);
        }
    }

    private void seedMenus() {
        if (menuRepository.count() > 0) {
            return;
        }

        log.info("Seeding System and Applicant Menus...");

        // 1. Applicant Portal Menu
        Menu applicantPortal = new Menu();
        applicantPortal.setTitle("Applicant Portal");
        applicantPortal.setIcon("school");
        applicantPortal.setRoute("/applicant/dashboard");
        applicantPortal.setOrderNo(1);
        applicantPortal.setPermissionCode("APPLICANT_VIEW");
        applicantPortal.setModule("Applicant Portal");
        applicantPortal = menuRepository.save(applicantPortal);

        createChildMenu(applicantPortal, "Dashboard", "dashboard", "/applicant/dashboard", 1, "APPLICANT_VIEW", "Applicant Portal");
        createChildMenu(applicantPortal, "Admission Test", "assignment", "/applicant/test", 2, "APPLICANT_VIEW", "Applicant Portal");
        createChildMenu(applicantPortal, "Merit Position", "emoji_events", "/applicant/merit-view", 3, "APPLICANT_VIEW", "Applicant Portal");
        createChildMenu(applicantPortal, "Choice Filling", "list_alt", "/applicant/choice-filling", 4, "APPLICANT_VIEW", "Applicant Portal");
        createChildMenu(applicantPortal, "Seat Allocation", "event_seat", "/applicant/allocation", 5, "APPLICANT_VIEW", "Applicant Portal");
        createChildMenu(applicantPortal, "Admission Confirmation", "check_circle", "/applicant/confirmation", 6, "APPLICANT_VIEW", "Applicant Portal");

        // 2. Main Dashboard
        Menu dashboard = new Menu();
        dashboard.setTitle("Dashboard");
        dashboard.setIcon("dashboard");
        dashboard.setRoute("/dashboard");
        dashboard.setOrderNo(2);
        dashboard.setPermissionCode("DASHBOARD_VIEW");
        dashboard.setModule("Dashboard");
        menuRepository.save(dashboard);

        // 3. Security Menu
        Menu security = new Menu();
        security.setTitle("Security");
        security.setIcon("security");
        security.setOrderNo(3);
        security.setPermissionCode("SECURITY_VIEW");
        security.setModule("Security");
        security = menuRepository.save(security);

        createChildMenu(security, "Users", "people", "/security/users", 1, "USER_VIEW", "Security");
        createChildMenu(security, "Roles", "admin_panel_settings", "/security/roles", 2, "ROLE_VIEW", "Security");
        createChildMenu(security, "Permissions", "vpn_key", "/security/permissions", 3, "PERMISSION_VIEW", "Security");
        createChildMenu(security, "Audit Logs", "history", "/security/audit-logs", 4, "AUDIT_VIEW", "Security");
        createChildMenu(security, "Menus", "menu", "/security/menus", 5, "MENU_MANAGE", "Security");
        createChildMenu(security, "Settings", "settings", "/security/settings", 6, "SETTINGS_MANAGE", "Security");

        // 4. Academic Menu
        Menu academic = new Menu();
        academic.setTitle("Academic");
        academic.setIcon("school");
        academic.setOrderNo(4);
        academic.setPermissionCode("ACADEMIC_VIEW");
        academic.setModule("Academic");
        academic = menuRepository.save(academic);

        createChildMenu(academic, "Dashboard", "dashboard", "/academic/dashboard", 1, "ACADEMIC_VIEW", "Academic");
        createChildMenu(academic, "Faculties", "account_balance", "/academic/faculty", 2, "ACADEMIC_VIEW", "Academic");
        createChildMenu(academic, "Departments", "domain", "/academic/departments", 3, "ACADEMIC_VIEW", "Academic");
        createChildMenu(academic, "Programs", "class", "/academic/programs", 4, "ACADEMIC_VIEW", "Academic");
        createChildMenu(academic, "Semesters", "date_range", "/academic/semesters", 5, "ACADEMIC_VIEW", "Academic");
        createChildMenu(academic, "Courses", "book", "/academic/courses", 6, "ACADEMIC_VIEW", "Academic");

        // 5. Admissions Menu
        Menu admissions = new Menu();
        admissions.setTitle("Admissions");
        admissions.setIcon("how_to_reg");
        admissions.setOrderNo(5);
        admissions.setPermissionCode("ADMISSION_VIEW");
        admissions.setModule("Admissions");
        admissions = menuRepository.save(admissions);

        createChildMenu(admissions, "Dashboard", "dashboard", "/admissions/dashboard", 1, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Pre-Admissions", "assignment_ind", "/admissions/pre-admissions", 2, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Applications", "description", "/admissions/applications", 3, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Tests", "quiz", "/admissions/tests", 4, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Test Results", "fact_check", "/admissions/test-results", 5, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Merit Lists", "format_list_numbered", "/admissions/merit-lists", 6, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Allocations", "event_seat", "/admissions/allocations", 7, "ADMISSION_VIEW", "Admissions");
        createChildMenu(admissions, "Confirmations", "verified", "/admissions/admission-confirmations", 8, "ADMISSION_VIEW", "Admissions");

        // 6. Students Menu
        Menu students = new Menu();
        students.setTitle("Students");
        students.setIcon("groups");
        students.setOrderNo(6);
        students.setPermissionCode("STUDENT_VIEW");
        students.setModule("Students");
        students = menuRepository.save(students);

        createChildMenu(students, "List", "list", "/students/list", 1, "STUDENT_VIEW", "Students");
        createChildMenu(students, "Dashboard", "dashboard", "/students/dashboard", 2, "STUDENT_VIEW", "Students");
        createChildMenu(students, "Profiles", "person", "/students/profiles", 3, "STUDENT_VIEW", "Students");
        createChildMenu(students, "Attendance", "fact_check", "/students/attendance", 4, "STUDENT_VIEW", "Students");
        createChildMenu(students, "Results", "assessment", "/students/result", 5, "STUDENT_VIEW", "Students");

        // 7. Teachers Menu
        Menu teachers = new Menu();
        teachers.setTitle("Teachers");
        teachers.setIcon("person_outline");
        teachers.setOrderNo(7);
        teachers.setPermissionCode("TEACHER_VIEW");
        teachers.setModule("Teachers");
        teachers = menuRepository.save(teachers);

        createChildMenu(teachers, "Dashboard", "dashboard", "/teachers/dashboard", 1, "TEACHER_VIEW", "Teachers");
        createChildMenu(teachers, "List", "list", "/teachers/list", 2, "TEACHER_VIEW", "Teachers");
        createChildMenu(teachers, "Routine", "schedule", "/teachers/class-routine", 3, "TEACHER_VIEW", "Teachers");
        createChildMenu(teachers, "Attendance", "fact_check", "/teachers/attendance", 4, "TEACHER_VIEW", "Teachers");

        // 8. HRM Menu
        Menu hrm = new Menu();
        hrm.setTitle("HRM");
        hrm.setIcon("badge");
        hrm.setOrderNo(8);
        hrm.setPermissionCode("HRM_VIEW");
        hrm.setModule("HRM");
        hrm = menuRepository.save(hrm);

        createChildMenu(hrm, "Employees", "badge", "/hrm/employees", 1, "HRM_VIEW", "HRM");
        createChildMenu(hrm, "Attendance", "fact_check", "/hrm/attendance", 2, "HRM_VIEW", "HRM");
        createChildMenu(hrm, "Leave Requests", "event_available", "/hrm/leave-requests", 3, "HRM_VIEW", "HRM");
        createChildMenu(hrm, "Payrolls", "payments", "/hrm/payrolls", 4, "HRM_VIEW", "HRM");

        // 9. Examination Menu
        Menu exam = new Menu();
        exam.setTitle("Examination");
        exam.setIcon("assignment");
        exam.setOrderNo(9);
        exam.setPermissionCode("EXAM_VIEW");
        exam.setModule("Examination");
        exam = menuRepository.save(exam);

        createChildMenu(exam, "Exams", "quiz", "/examination/exams", 1, "EXAM_VIEW", "Examination");
        createChildMenu(exam, "Schedules", "schedule", "/examination/schedules", 2, "EXAM_VIEW", "Examination");
        createChildMenu(exam, "Marks", "edit_note", "/examination/marks", 3, "EXAM_VIEW", "Examination");
        createChildMenu(exam, "Results", "grade", "/examination/results", 4, "EXAM_VIEW", "Examination");

        // 10. LMS Menu
        Menu lms = new Menu();
        lms.setTitle("LMS");
        lms.setIcon("laptop_chromebook");
        lms.setOrderNo(10);
        lms.setPermissionCode("LMS_VIEW");
        lms.setModule("LMS");
        lms = menuRepository.save(lms);

        createChildMenu(lms, "Assignments", "assignment", "/lms/assignments", 1, "LMS_VIEW", "LMS");
        createChildMenu(lms, "Submissions", "upload_file", "/lms/submissions", 2, "LMS_VIEW", "LMS");
        createChildMenu(lms, "Materials", "folder", "/lms/materials", 3, "LMS_VIEW", "LMS");
        createChildMenu(lms, "Online Classes", "videocam", "/lms/online-classes", 4, "LMS_VIEW", "LMS");

        // 11. Finance Menu
        Menu finance = new Menu();
        finance.setTitle("Finance");
        finance.setIcon("account_balance_wallet");
        finance.setOrderNo(11);
        finance.setPermissionCode("FINANCE_VIEW");
        finance.setModule("Finance");
        finance = menuRepository.save(finance);

        createChildMenu(finance, "Fee Types", "category", "/finance/fee-types", 1, "FINANCE_VIEW", "Finance");
        createChildMenu(finance, "Invoices", "receipt_long", "/finance/invoices", 2, "FINANCE_VIEW", "Finance");
        createChildMenu(finance, "Payments", "payment", "/finance/payments", 3, "FINANCE_VIEW", "Finance");
        createChildMenu(finance, "Transactions", "swap_horiz", "/finance/transactions", 4, "FINANCE_VIEW", "Finance");

        // 12. Communication Menu
        Menu comm = new Menu();
        comm.setTitle("Communication");
        comm.setIcon("campaign");
        comm.setOrderNo(12);
        comm.setPermissionCode("COMMUNICATION_VIEW");
        comm.setModule("Communication");
        comm = menuRepository.save(comm);

        createChildMenu(comm, "Notices", "campaign", "/communication/notices", 1, "COMMUNICATION_VIEW", "Communication");
        createChildMenu(comm, "Messages", "mail", "/communication/messages", 2, "COMMUNICATION_VIEW", "Communication");
        createChildMenu(comm, "Notifications", "notifications", "/communication/notifications", 3, "COMMUNICATION_VIEW", "Communication");
    }

    private void createChildMenu(Menu parent, String title, String icon, String route, int orderNo, String permissionCode, String module) {
        Menu child = new Menu();
        child.setParent(parent);
        child.setTitle(title);
        child.setIcon(icon);
        child.setRoute(route);
        child.setOrderNo(orderNo);
        child.setPermissionCode(permissionCode);
        child.setModule(module);
        menuRepository.save(child);
    }

    private void seedPreAdmissionsAndApplications() {
        AdmissionCircular circular = null;
        if (admissionCircularRepository.count() == 0) {
            log.info("Seeding Admission Circular...");
            circular = new AdmissionCircular();
            circular.setTitle("Undergraduate Admission Circular - Spring 2026");
            circular.setUniqueCode("CIRC-2026-001");
            circular.setDescription("Applications are invited from eligible candidates for admission into Undergraduate Programs for Spring 2026 semester.");
            circular.setEligibility("Minimum GPA 3.5 in SSC and HSC from Science/Commerce/Arts background.");
            circular.setRequiredDocuments("SSC and HSC Marksheets, Certificate, National ID / Birth Certificate, 2 Passport size photos.");
            circular.setAdmissionProcess("1. Online Pre-registration -> 2. Application Submission -> 3. Merit List Publication -> 4. Enrollment.");
            circular.setPublishDate(java.time.LocalDate.now().minusDays(15));
            circular.setValidUntil(java.time.LocalDate.now().plusMonths(2));
            circular.setStatus("PUBLISHED");
            circular.setIsPublished(true);
            circular.setSessionId(1L);
            circular.setProgramId(1L);
            circular = admissionCircularRepository.save(circular);
        } else {
            circular = admissionCircularRepository.findAll().stream().findFirst().orElse(null);
        }
        Long circId = circular != null ? circular.getId() : 1L;

        if (preAdmissionRegistrationRepository.count() == 0) {
            log.info("Seeding PreAdmission Registrations...");

            PreAdmissionRegistration r1 = new PreAdmissionRegistration();
            r1.setRegistrationNumber("REG-67C93EB1");
            r1.setTrackingNumber("TRK-1001");
            r1.setFirstName("Emon");
            r1.setLastName("Sarkar");
            r1.setEmail("emonsarkar@gmail.com");
            r1.setPhone("01711112222");
            r1.setSscGpa(5.0);
            r1.setHscGpa(5.0);
            r1.setCircularId(circId);
            r1.setStatus("APPROVED");
            r1.setProgramPreference1("B.Sc. in Computer Science & Engineering");
            preAdmissionRegistrationRepository.save(r1);

            PreAdmissionRegistration r2 = new PreAdmissionRegistration();
            r2.setRegistrationNumber("REG-2026-002");
            r2.setTrackingNumber("TRK-1002");
            r2.setFirstName("Tariqul");
            r2.setLastName("Islam");
            r2.setEmail("tariqul@gmail.com");
            r2.setPhone("01722223333");
            r2.setSscGpa(4.8);
            r2.setHscGpa(4.9);
            r2.setCircularId(circId);
            r2.setStatus("SUBMITTED");
            r2.setProgramPreference1("B.Sc. in Electrical & Electronic Engineering");
            preAdmissionRegistrationRepository.save(r2);

            PreAdmissionRegistration r3 = new PreAdmissionRegistration();
            r3.setRegistrationNumber("REG-2026-003");
            r3.setTrackingNumber("TRK-1003");
            r3.setFirstName("Nusrat");
            r3.setLastName("Jahan");
            r3.setEmail("nusrat@gmail.com");
            r3.setPhone("01733334444");
            r3.setSscGpa(5.0);
            r3.setHscGpa(4.7);
            r3.setCircularId(circId);
            r3.setStatus("SUBMITTED");
            r3.setProgramPreference1("Bachelor of Business Administration (BBA)");
            preAdmissionRegistrationRepository.save(r3);
        }

        if (admissionTestRepository.count() == 0) {
            log.info("Seeding Admission Test...");
            AdmissionTest t1 = new AdmissionTest();
            t1.setName("General Admission Test Spring 2026");
            t1.setAcademicYear("2026");
            t1.setTestType("WRITTEN_MCQ");
            t1.setTestDate(LocalDateTime.now().plusDays(7));
            t1.setStartTime("10:00 AM");
            t1.setEndTime("12:00 PM");
            t1.setDurationMinutes(120);
            t1.setTotalMarks(100);
            t1.setPassingMarks(40);
            t1.setStatus("PUBLISHED");
            admissionTestRepository.save(t1);
        }

        if (admissionApplicationRepository.count() == 0) {
            log.info("Seeding Admission Applications...");
            AdmissionApplication a1 = new AdmissionApplication();
            a1.setApplicationNumber("APP-2026-001");
            a1.setUniqueCode("UC-1001");
            a1.setCandidateId(1L);
            a1.setCircularId(circId);
            a1.setSessionId(1L);
            a1.setProgramId(1L);
            a1.setStatus("APPROVED");
            a1.setIsSubmitted(true);
            a1.setIsVerified(true);
            a1.setTestScore(85.0);
            a1.setMeritScore(92.5);
            admissionApplicationRepository.save(a1);

            AdmissionApplication a2 = new AdmissionApplication();
            a2.setApplicationNumber("APP-2026-002");
            a2.setUniqueCode("UC-1002");
            a2.setCandidateId(2L);
            a2.setCircularId(circId);
            a2.setSessionId(1L);
            a2.setProgramId(1L);
            a2.setStatus("APPROVED");
            a2.setIsSubmitted(true);
            a2.setIsVerified(true);
            a2.setTestScore(78.0);
            a2.setMeritScore(86.0);
            admissionApplicationRepository.save(a2);
        }

        AdmissionMeritList ml = null;
        if (admissionMeritListRepository.count() == 0) {
            log.info("Seeding Admission Merit List...");
            ml = new AdmissionMeritList();
            ml.setName("Merit List 1 - Spring 2026");
            ml.setAcademicYear("2026");
            ml.setCircularId(circId);
            ml.setStatus("PUBLISHED");
            ml.setTotalSeats(100);
            ml.setTotalApplicants(3);
            ml.setSelectedCount(2);
            ml.setWaitingCount(1);
            ml.setCutoffScore(70.0);
            ml.setPublishedAt(LocalDateTime.now());
            ml = admissionMeritListRepository.save(ml);
        } else {
            ml = admissionMeritListRepository.findAll().stream().findFirst().orElse(null);
            if (ml != null && ml.getCircularId() == null) {
                ml.setCircularId(circId);
                admissionMeritListRepository.save(ml);
            }
        }

        if (admissionMeritListEntryRepository.count() == 0 && ml != null) {
            log.info("Seeding Admission Merit List Entries...");
            Long mlId = ml.getId();

            AdmissionMeritListEntry e1 = new AdmissionMeritListEntry();
            e1.setMeritListId(mlId);
            e1.setRegistrationId(1L);
            e1.setRank(1);
            e1.setRollNumber("REG-67C93EB1");
            e1.setApplicationNumber("APP-2026-001");
            e1.setApplicantName("Tanvir Ahmed");
            e1.setFacultyName("Faculty of Science & Engineering");
            e1.setDepartmentName("Computer Science & Engineering");
            e1.setProgramName("B.Sc. in Computer Science & Engineering");
            e1.setShift("DAY");
            e1.setTestMarks(85.0);
            e1.setTestMaxMarks(100.0);
            e1.setScore(85.0);
            e1.setAcademicScore(9.8);
            e1.setTotalWeightedScore(92.5);
            e1.setSscGpa(5.0);
            e1.setHscGpa(4.8);
            e1.setQuotaType("GENERAL");
            e1.setStatus("SELECTED");
            e1.setIsOffered(true);
            e1.setIsEnrolled(false);
            e1.setRemarks("First Merit List - Selected");
            admissionMeritListEntryRepository.save(e1);

            AdmissionMeritListEntry e2 = new AdmissionMeritListEntry();
            e2.setMeritListId(mlId);
            e2.setRegistrationId(2L);
            e2.setRank(2);
            e2.setRollNumber("REG-2026-002");
            e2.setApplicationNumber("APP-2026-002");
            e2.setApplicantName("Tariqul Islam");
            e2.setFacultyName("Faculty of Science & Engineering");
            e2.setDepartmentName("Electrical & Electronic Engineering");
            e2.setProgramName("B.Sc. in Electrical & Electronic Engineering");
            e2.setShift("DAY");
            e2.setTestMarks(78.0);
            e2.setTestMaxMarks(100.0);
            e2.setScore(78.0);
            e2.setAcademicScore(9.7);
            e2.setTotalWeightedScore(86.0);
            e2.setSscGpa(4.8);
            e2.setHscGpa(4.9);
            e2.setQuotaType("GENERAL");
            e2.setStatus("SELECTED");
            e2.setIsOffered(true);
            e2.setIsEnrolled(false);
            e2.setRemarks("First Merit List - Selected");
            admissionMeritListEntryRepository.save(e2);

            AdmissionMeritListEntry e3 = new AdmissionMeritListEntry();
            e3.setMeritListId(mlId);
            e3.setRegistrationId(3L);
            e3.setRank(3);
            e3.setRollNumber("REG-2026-003");
            e3.setApplicationNumber("APP-2026-003");
            e3.setApplicantName("Nusrat Jahan");
            e3.setFacultyName("Faculty of Business Administration");
            e3.setDepartmentName("Business Administration");
            e3.setProgramName("Bachelor of Business Administration (BBA)");
            e3.setShift("DAY");
            e3.setTestMarks(68.0);
            e3.setTestMaxMarks(100.0);
            e3.setScore(68.0);
            e3.setAcademicScore(9.7);
            e3.setTotalWeightedScore(77.7);
            e3.setSscGpa(5.0);
            e3.setHscGpa(4.7);
            e3.setQuotaType("GENERAL");
            e3.setStatus("WAITING");
            e3.setIsOffered(false);
            e3.setIsEnrolled(false);
            e3.setRemarks("Waiting List 1");
            admissionMeritListEntryRepository.save(e3);
        }

        if (seatAllocationRepository.count() == 0) {
            log.info("Seeding Seat Allocations...");
            SeatAllocation sa1 = new SeatAllocation();
            sa1.setRegistrationId(1L);
            sa1.setCenterName("Main Academic Building");
            sa1.setBuildingName("Block A");
            sa1.setRoomName("Room 301");
            sa1.setSeatNumber("A-12");
            sa1.setRollNumber("REG-67C93EB1");
            sa1.setStatus("ALLOCATED");
            seatAllocationRepository.save(sa1);

            SeatAllocation sa2 = new SeatAllocation();
            sa2.setRegistrationId(2L);
            sa2.setCenterName("Main Academic Building");
            sa2.setBuildingName("Block A");
            sa2.setRoomName("Room 301");
            sa2.setSeatNumber("A-13");
            sa2.setRollNumber("REG-2026-002");
            sa2.setStatus("ALLOCATED");
            seatAllocationRepository.save(sa2);
        }

        // 4. Seed Academic Sessions, Students, Courses, Examinations, GradeRules, and Results
        seedAcademicAndExaminationData();
    }

    private void seedAcademicAndExaminationData() {
        if (examinationResultRepository.count() > 0) {
            return;
        }
        log.info("Seeding Academic Sessions, Students, Courses, Examinations, GradeRules, and ExaminationResults...");

        // 1. Academic Session
        AcademicSession session = academicSessionRepository.findAll().stream().findFirst().orElse(null);
        if (session == null) {
            session = new AcademicSession();
            session.setSessionName("2025-2026");
            session.setStartDate(java.time.LocalDate.of(2025, 1, 1));
            session.setEndDate(java.time.LocalDate.of(2026, 12, 31));
            session.setActive(true);
            session = academicSessionRepository.save(session);
        }

        // Program & Department
        Program program = programRepository.findAll().stream().findFirst().orElse(null);
        Department dept = departmentRepository.findAll().stream().findFirst().orElse(null);

        // 2. Student Users
        User studentUser1 = userRepository.findByUsername("student1").orElse(null);
        if (studentUser1 == null) {
            studentUser1 = userRepository.findByEmail("pk@gmail.com").orElse(null);
        }
        if (studentUser1 == null) {
            studentUser1 = new User();
            studentUser1.setUsername("student1");
            studentUser1.setEmail("pk@gmail.com");
            studentUser1.setPassword(passwordEncoder.encode("Password@123"));
            studentUser1.setPhone("+8801711111111");
            studentUser1.setEnabled(true);
            studentUser1.setAccountNonLocked(true);
            studentUser1.setAccountNonExpired(true);
            studentUser1.setCredentialsNonExpired(true);
            studentUser1.setRole(UserRole.STUDENT);
            studentUser1 = userRepository.save(studentUser1);
        }

        User studentUser2 = userRepository.findByUsername("student.b").orElse(null);
        if (studentUser2 == null) {
            studentUser2 = userRepository.findByEmail("studentb@eliteuniversity.edu").orElse(null);
        }
        if (studentUser2 == null) {
            studentUser2 = new User();
            studentUser2.setUsername("student.b");
            studentUser2.setEmail("studentb@eliteuniversity.edu");
            studentUser2.setPassword(passwordEncoder.encode("Password@123"));
            studentUser2.setPhone("+8801722222222");
            studentUser2.setEnabled(true);
            studentUser2.setAccountNonLocked(true);
            studentUser2.setAccountNonExpired(true);
            studentUser2.setCredentialsNonExpired(true);
            studentUser2.setRole(UserRole.STUDENT);
            studentUser2 = userRepository.save(studentUser2);
        }

        // 3. Students
        Student s1 = studentRepository.findByStudentId("STU-2025-001");
        if (s1 == null) {
            s1 = new Student();
            s1.setStudentId("STU-2025-001");
            s1.setFullName("Test Student A");
            s1.setPhone("+8801711111111");
            s1.setAdmissionDate(java.time.LocalDate.of(2025, 1, 15));
            s1.setUser(studentUser1);
            s1.setProgram(program);
            s1.setAcademicSession(session);
            s1 = studentRepository.save(s1);
        }

        Student s2 = studentRepository.findByStudentId("STU-2025-002");
        if (s2 == null) {
            s2 = new Student();
            s2.setStudentId("STU-2025-002");
            s2.setFullName("Test Student B");
            s2.setPhone("+8801722222222");
            s2.setAdmissionDate(java.time.LocalDate.of(2025, 1, 15));
            s2.setUser(studentUser2);
            s2.setProgram(program);
            s2.setAcademicSession(session);
            s2 = studentRepository.save(s2);
        }

        // 4. Courses
        Course c1 = courseRepository.findByCourseCode("CSE 225");
        if (c1 == null) {
            c1 = new Course();
            c1.setCourseCode("CSE 225");
            c1.setCourseName("Data Structures and Algorithms");
            c1.setCredit(3);
            c1.setDepartment(dept);
            c1.setProgram(program);
            c1 = courseRepository.save(c1);
        }

        Course c2 = courseRepository.findByCourseCode("CSE 231");
        if (c2 == null) {
            c2 = new Course();
            c2.setCourseCode("CSE 231");
            c2.setCourseName("Digital Logic Design");
            c2.setCredit(3);
            c2.setDepartment(dept);
            c2.setProgram(program);
            c2 = courseRepository.save(c2);
        }

        Course c3 = courseRepository.findByCourseCode("CSE 241");
        if (c3 == null) {
            c3 = new Course();
            c3.setCourseCode("CSE 241");
            c3.setCourseName("Object Oriented Programming");
            c3.setCredit(3);
            c3.setDepartment(dept);
            c3.setProgram(program);
            c3 = courseRepository.save(c3);
        }

        // 5. Grade Rules
        seedGradeRules(c1);
        seedGradeRules(c2);
        seedGradeRules(c3);

        // 6. Examinations (Spring 2025 & Fall 2025)
        Examination exSpring1 = getOrCreateExam("CSE225 Final Spring", "Spring 2025", 100.0, 40.0, c1);
        Examination exSpring2 = getOrCreateExam("CSE231 Final Spring", "Spring 2025", 100.0, 40.0, c2);
        Examination exSpring3 = getOrCreateExam("CSE241 Final Spring", "Spring 2025", 100.0, 40.0, c3);

        Examination exFall1 = getOrCreateExam("CSE225 Final Fall", "Fall 2025", 100.0, 40.0, c1);
        Examination exFall2 = getOrCreateExam("CSE231 Final Fall", "Fall 2025", 100.0, 40.0, c2);
        Examination exFall3 = getOrCreateExam("CSE241 Final Fall", "Fall 2025", 100.0, 40.0, c3);

        // 7. Examination Results for Student A (High GPA)
        saveExamResult(s1, exSpring1, 85.0, "A", 4.0, 3.0);
        saveExamResult(s1, exSpring2, 78.0, "A-", 3.7, 3.0);
        saveExamResult(s1, exSpring3, 92.0, "A", 4.0, 3.0);

        saveExamResult(s1, exFall1, 88.0, "A", 4.0, 3.0);
        saveExamResult(s1, exFall2, 65.0, "B-", 3.0, 3.0);
        saveExamResult(s1, exFall3, 55.0, "D", 1.0, 3.0);

        // 8. Examination Results for Student B
        saveExamResult(s2, exSpring1, 60.0, "C", 2.0, 3.0);
        saveExamResult(s2, exSpring2, 50.0, "D", 1.0, 3.0);
        saveExamResult(s2, exSpring3, 72.0, "B", 3.3, 3.0);

        saveExamResult(s2, exFall1, 80.0, "A", 4.0, 3.0);
        saveExamResult(s2, exFall2, 45.0, "F", 0.0, 3.0);
        saveExamResult(s2, exFall3, 67.0, "B-", 3.0, 3.0);

        log.info("Academic and examination results seeded successfully.");
    }

    private void seedGradeRules(Course course) {
        if (course == null || !gradeRuleRepository.findByCourseId(course.getId()).isEmpty()) {
            return;
        }
        createGradeRule(course, "A", 80, 100, 4.0);
        createGradeRule(course, "A-", 75, 79, 3.7);
        createGradeRule(course, "B", 70, 74, 3.3);
        createGradeRule(course, "B-", 65, 69, 3.0);
        createGradeRule(course, "C", 60, 64, 2.0);
        createGradeRule(course, "D", 50, 59, 1.0);
        createGradeRule(course, "F", 0, 49, 0.0);
    }

    private void createGradeRule(Course course, String grade, double min, double max, double gp) {
        GradeRule r = new GradeRule();
        r.setCourse(course);
        r.setGrade(grade);
        r.setMinPercentage(java.math.BigDecimal.valueOf(min));
        r.setMaxPercentage(java.math.BigDecimal.valueOf(max));
        r.setGradePoint(java.math.BigDecimal.valueOf(gp));
        r.setUniqueCode("GR-" + course.getId() + "-" + grade);
        gradeRuleRepository.save(r);
    }

    private Examination getOrCreateExam(String name, String semester, Double totalMarks, Double passMarks, Course course) {
        List<Examination> list = examinationRepository.findByCourseId(course.getId());
        for (Examination ex : list) {
            if (semester.equals(ex.getSemester()) && name.equals(ex.getExaminationName())) {
                return ex;
            }
        }
        Examination ex = new Examination();
        ex.setExaminationName(name);
        ex.setSemester(semester);
        ex.setTotalMarks(totalMarks);
        ex.setPassMarks(passMarks);
        ex.setCourse(course);
        return examinationRepository.save(ex);
    }

    private void saveExamResult(Student student, Examination exam, Double marks, String grade, Double gradePoint, Double credit) {
        ExaminationResult er = new ExaminationResult();
        er.setStudent(student);
        er.setExamination(exam);
        er.setMarks(marks);
        er.setGrade(grade);
        er.setGradePoint(gradePoint);
        er.setCredit(credit);
        examinationResultRepository.save(er);
    }

    private void ensureMissingMenus() {
        if (!menuRepository.existsByRoute("/admissions/test-results")) {
            menuRepository.findByTitle("Admissions").ifPresent(admissions -> {
                Menu child = new Menu();
                child.setParent(admissions);
                child.setTitle("Test Results");
                child.setIcon("fact_check");
                child.setRoute("/admissions/test-results");
                child.setOrderNo(5);
                child.setPermissionCode("ADMISSION_VIEW");
                child.setModule("Admissions");
                menuRepository.save(child);
                log.info("Ensured missing sidebar menu 'Test Results' (/admissions/test-results)");
            });
        }
    }

    private void ensureChoiceFillingConfig() {
        if (choiceFillingConfigRepository.count() == 0 || choiceFillingConfigRepository.findByIsActiveTrue().isEmpty()) {
            com.brilliantsofts.EliteUniversity.entity.ChoiceFillingConfig config = new com.brilliantsofts.EliteUniversity.entity.ChoiceFillingConfig();
            config.setSessionId(1L);
            config.setChoiceStartDate(LocalDateTime.now().minusDays(10));
            config.setChoiceEndDate(LocalDateTime.now().plusDays(60));
            config.setMinChoices(1);
            config.setMaxChoices(5);
            config.setAllowEditingBeforeDeadline(true);
            config.setAutoLockAfterDeadline(true);
            config.setIncludeWaitingList(true);
            config.setStatus("ACTIVE");
            config.setIsActive(true);
            config.setRemarks("2025-2026 Undergraduate General Choice Filling Window");
            choiceFillingConfigRepository.save(config);
            log.info("Ensured active ChoiceFillingConfig initialized");
        }
    }
}
