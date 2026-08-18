package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.response.ResultSheetCourseRow;
import com.brilliantsofts.EliteUniversity.dto.response.ResultSheetResponse;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResultSummary;
import com.brilliantsofts.EliteUniversity.entity.Course;
import com.brilliantsofts.EliteUniversity.entity.Examination;
import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;
import com.brilliantsofts.EliteUniversity.entity.GradeRule;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.repository.ExaminationRepository;
import com.brilliantsofts.EliteUniversity.repository.ExaminationResultRepository;
import com.brilliantsofts.EliteUniversity.repository.GradeRuleRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.service.ResultSheetService;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ResultSheetServiceImpl implements ResultSheetService {

    private final StudentRepository studentRepository;
    private final ExaminationResultRepository examinationResultRepository;
    private final ExaminationRepository examinationRepository;
    private final GradeRuleRepository gradeRuleRepository;

    @Override
    public ResultSheetResponse getForStudent(Long studentId, String semester) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + studentId));

        List<ExaminationResult> all = examinationResultRepository.findByStudentId(studentId);

        List<String> availableSemesters = all.stream()
                .map(er -> er.getExamination() != null ? er.getExamination().getSemester() : null)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .sorted(this::compareSemester)
                .toList();

        String selected = (semester == null || semester.isBlank() || "ALL".equalsIgnoreCase(semester))
                ? (availableSemesters.isEmpty() ? null : availableSemesters.get(availableSemesters.size() - 1))
                : semester;

        List<ExaminationResult> selectedRows = (selected == null) ? all
                : all.stream()
                .filter(er -> selected.equals(er.getExamination() != null ? er.getExamination().getSemester() : null))
                .toList();

        List<ResultSheetCourseRow> rows = selectedRows.stream().map(this::toRow).toList();

        // Aggregate per semester: { sum(credit * gradePoint), sum(credit) }
        Map<String, double[]> perSem = new LinkedHashMap<>();
        for (ExaminationResult er : all) {
            String sem = er.getExamination() != null ? er.getExamination().getSemester() : null;
            if (sem == null || sem.isBlank()) continue;
            double[] agg = perSem.computeIfAbsent(sem, k -> new double[]{0, 0});
            double credit = resolveCredit(er);
            double gp = resolveGradePoint(er);
            agg[0] += credit * gp;
            agg[1] += credit;
        }

        double semesterGpa = 0;
        double semCredits = 0;
        if (selected != null && perSem.containsKey(selected)) {
            double[] a = perSem.get(selected);
            semesterGpa = a[1] > 0 ? a[0] / a[1] : 0;
            semCredits = a[1];
        }

        double cumCG = 0;
        double cumC = 0;
        for (double[] a : perSem.values()) {
            cumCG += a[0];
            cumC += a[1];
        }
        double currentCgpa = cumC > 0 ? cumCG / cumC : 0;

        double prevCG = 0;
        double prevC = 0;
        if (selected != null) {
            for (String s : availableSemesters) {
                if (s.equals(selected)) break;
                double[] a = perSem.get(s);
                if (a != null) {
                    prevCG += a[0];
                    prevC += a[1];
                }
            }
        }
        Double previousCgpa = prevC > 0 ? prevCG / prevC : null;

        double totalRegisteredCredits = semCredits;
        double totalCompletedCredits = cumC;
        double totalEarnedCredits = 0;
        for (ExaminationResult er : all) {
            if (resolveGradePoint(er) > 0) totalEarnedCredits += resolveCredit(er);
        }

        ResultSheetResponse resp = new ResultSheetResponse();
        resp.setStudentDbId(student.getId());
        resp.setStudentId(student.getStudentId());
        resp.setFullName(student.getFullName());
        resp.setProgramName(student.getProgram() != null ? student.getProgram().getName() : null);
        resp.setDepartmentName(student.getProgram() != null && student.getProgram().getDepartment() != null
                ? student.getProgram().getDepartment().getName() : null);
        resp.setFacultyName(student.getProgram() != null && student.getProgram().getDepartment() != null
                && student.getProgram().getDepartment().getFaculty() != null
                ? student.getProgram().getDepartment().getFaculty().getName() : null);
        resp.setAcademicSessionName(student.getAcademicSession() != null ? student.getAcademicSession().getSessionName() : null);
        resp.setSemester(selected);
        resp.setAvailableSemesters(availableSemesters);
        resp.setCourseRows(rows);
        resp.setTotalRegisteredCredits(round(totalRegisteredCredits));
        resp.setTotalCompletedCredits(round(totalCompletedCredits));
        resp.setTotalEarnedCredits(round(totalEarnedCredits));
        resp.setSemesterGpa(round(semesterGpa));
        resp.setPreviousCgpa(previousCgpa == null ? null : round(previousCgpa));
        resp.setCurrentCgpa(round(currentCgpa));
        resp.setAcademicStatus(computeStatus(selectedRows));
        return resp;
    }

    @Override
    public Page<StudentResultSummary> getStudentSummaries(int page, int size, String search,
                                                          Long facultyId, Long departmentId, Long programId,
                                                          Long academicSessionId, String semester, String status) {
        Specification<Student> spec = (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();
            if (programId != null) preds.add(cb.equal(root.get("program").get("id"), programId));
            if (departmentId != null) preds.add(cb.equal(root.get("program").get("department").get("id"), departmentId));
            if (facultyId != null) preds.add(cb.equal(root.get("program").get("department").get("faculty").get("id"), facultyId));
            if (academicSessionId != null) preds.add(cb.equal(root.get("academicSession").get("id"), academicSessionId));
            if (search != null && !search.isBlank()) {
                String like = "%" + search.toLowerCase() + "%";
                preds.add(cb.or(
                        cb.like(cb.lower(root.get("studentId")), like),
                        cb.like(cb.lower(root.get("fullName")), like)));
            }
            if (semester != null && !semester.isBlank()) {
                Subquery<Long> sub = query.subquery(Long.class);
                var er = sub.from(ExaminationResult.class);
                sub.select(er.get("id"));
                sub.where(cb.equal(er.get("student").get("id"), root.get("id")),
                        cb.equal(er.get("examination").get("semester"), semester));
                preds.add(cb.exists(sub));
            }
            return cb.and(preds.toArray(new Predicate[0]));
        };

        Page<Student> studentPage = studentRepository.findAll(spec, PageRequest.of(page, size));
        List<StudentResultSummary> content = new ArrayList<>();
        for (Student s : studentPage.getContent()) {
            ResultSheetResponse full = getForStudent(s.getId(), null);
            StudentResultSummary sum = new StudentResultSummary();
            sum.setStudentDbId(s.getId());
            sum.setStudentId(s.getStudentId());
            sum.setFullName(s.getFullName());
            sum.setProgramName(s.getProgram() != null ? s.getProgram().getName() : null);
            sum.setDepartmentName(s.getProgram() != null && s.getProgram().getDepartment() != null
                    ? s.getProgram().getDepartment().getName() : null);
            sum.setFacultyName(s.getProgram() != null && s.getProgram().getDepartment() != null
                    && s.getProgram().getDepartment().getFaculty() != null
                    ? s.getProgram().getDepartment().getFaculty().getName() : null);
            sum.setAcademicSessionName(s.getAcademicSession() != null ? s.getAcademicSession().getSessionName() : null);
            sum.setCurrentSemester(full.getSemester());
            sum.setSemesterGpa(full.getSemesterGpa());
            sum.setCgpa(full.getCurrentCgpa());
            sum.setStatus(full.getAcademicStatus());
            content.add(sum);
        }
        if (status != null && !status.isBlank()) {
            content = content.stream()
                    .filter(c -> status.equalsIgnoreCase(c.getStatus()))
                    .toList();
        }
        return new PageImpl<>(content, studentPage.getPageable(), studentPage.getTotalElements());
    }

    @Override
    public Double getCgpa(Long studentId) {
        return getForStudent(studentId, null).getCurrentCgpa();
    }

    @Override
    public List<String> getSemesters() {
        return examinationRepository.findAll().stream()
                .map(Examination::getSemester)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .sorted(this::compareSemester)
                .toList();
    }

    private ResultSheetCourseRow toRow(ExaminationResult er) {
        ResultSheetCourseRow row = new ResultSheetCourseRow();
        Examination examination = er.getExamination();
        Course course = examination != null ? examination.getCourse() : null;
        row.setCourseCode(course != null ? course.getCourseCode() : null);
        row.setCourseTitle(course != null ? course.getCourseName() : null);
        double credit = resolveCredit(er);
        double gp = resolveGradePoint(er);
        row.setCredit(credit);
        row.setMarksObtained(er.getMarks());
        row.setTotalMarks(examination != null ? examination.getTotalMarks() : null);
        row.setLetterGrade(er.getGrade() != null ? er.getGrade() : deriveGrade(er));
        row.setGradePoint(gp);
        row.setCreditXGradePoint(round(credit * gp));
        return row;
    }

    private double resolveCredit(ExaminationResult er) {
        if (er.getCredit() != null && er.getCredit() > 0) return er.getCredit();
        Course c = er.getExamination() != null ? er.getExamination().getCourse() : null;
        if (c != null && c.getCredit() != null) return c.getCredit();
        return 0.0;
    }

    private double resolveGradePoint(ExaminationResult er) {
        if (er.getGradePoint() != null) return er.getGradePoint();
        Double derived = deriveGradePoint(er);
        return derived != null ? derived : 0.0;
    }

    private Double deriveGradePoint(ExaminationResult er) {
        Examination ex = er.getExamination();
        if (ex == null || ex.getCourse() == null || er.getMarks() == null
                || ex.getTotalMarks() == null || ex.getTotalMarks() <= 0) return null;
        double pct = (er.getMarks() / ex.getTotalMarks()) * 100.0;
        List<GradeRule> rules = gradeRuleRepository.findByCourseId(ex.getCourse().getId());
        for (GradeRule r : rules) {
            double min = r.getMinPercentage() != null ? r.getMinPercentage().doubleValue() : 0;
            double max = r.getMaxPercentage() != null ? r.getMaxPercentage().doubleValue() : 100;
            if (pct >= min && pct <= max) {
                return r.getGradePoint() != null ? r.getGradePoint().doubleValue() : null;
            }
        }
        return null;
    }

    private String deriveGrade(ExaminationResult er) {
        Examination ex = er.getExamination();
        if (ex == null || ex.getCourse() == null || er.getMarks() == null
                || ex.getTotalMarks() == null || ex.getTotalMarks() <= 0) return null;
        double pct = (er.getMarks() / ex.getTotalMarks()) * 100.0;
        List<GradeRule> rules = gradeRuleRepository.findByCourseId(ex.getCourse().getId());
        for (GradeRule r : rules) {
            double min = r.getMinPercentage() != null ? r.getMinPercentage().doubleValue() : 0;
            double max = r.getMaxPercentage() != null ? r.getMaxPercentage().doubleValue() : 100;
            if (pct >= min && pct <= max) return r.getGrade();
        }
        return null;
    }

    private String computeStatus(List<ExaminationResult> rows) {
        if (rows == null || rows.isEmpty()) return "INCOMPLETE";
        for (ExaminationResult er : rows) {
            if (resolveGradePoint(er) <= 0) return "FAIL";
        }
        return "PASS";
    }

    private Double round(Double d) {
        if (d == null) return null;
        return Math.round(d * 100.0) / 100.0;
    }

    private int compareSemester(String a, String b) {
        return semesterOrder(a).compareTo(semesterOrder(b));
    }

    private Integer semesterOrder(String s) {
        if (s == null) return 0;
        int year = 0;
        int season = 0;
        for (String p : s.trim().split("\\s+")) {
            if (p.matches("\\d{4}")) {
                year = Integer.parseInt(p);
            } else {
                switch (p.toLowerCase()) {
                    case "spring": season = 1; break;
                    case "summer": season = 2; break;
                    case "fall": season = 3; break;
                    case "winter": season = 4; break;
                    default: season = 0;
                }
            }
        }
        return year * 10 + season;
    }

    @Override
    public byte[] generateResultSheetPdf(Long studentId, String semester) {
        String html = generateResultSheetHtml(studentId, semester);
        return html.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    @Override
    public String generateResultSheetHtml(Long studentId, String semester) {
        ResultSheetResponse res = getForStudent(studentId, semester);
        if (res == null) {
            throw new RuntimeException("Student result sheet not found for ID: " + studentId);
        }

        StringBuilder courseRowsHtml = new StringBuilder();
        if (res.getCourseRows() != null && !res.getCourseRows().isEmpty()) {
            int idx = 1;
            for (ResultSheetCourseRow row : res.getCourseRows()) {
                String code = row.getCourseCode() != null ? row.getCourseCode() : "N/A";
                String title = row.getCourseTitle() != null ? row.getCourseTitle() : "Course";
                double cr = row.getCredit() != null ? row.getCredit() : 0.0;
                double marks = row.getMarksObtained() != null ? row.getMarksObtained() : 0.0;
                double totalMarks = row.getTotalMarks() != null ? row.getTotalMarks() : 100.0;
                String grade = row.getLetterGrade() != null ? row.getLetterGrade() : "N/A";
                double gp = row.getGradePoint() != null ? row.getGradePoint() : 0.0;
                double crXgp = row.getCreditXGradePoint() != null ? row.getCreditXGradePoint() : (cr * gp);

                String gradeBadgeColor = "#16a34a";
                if ("F".equalsIgnoreCase(grade)) gradeBadgeColor = "#dc2626";
                else if (grade.startsWith("C") || grade.startsWith("D")) gradeBadgeColor = "#d97706";

                courseRowsHtml.append(String.format("""
                    <tr>
                        <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#64748b;">%d</td>
                        <td style="padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:700; color:#0f172a;">%s</td>
                        <td style="padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#334155;">%s</td>
                        <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:600;">%.1f</td>
                        <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#475569;">%.0f / %.0f</td>
                        <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0;"><span style="display:inline-block; padding:2px 10px; border-radius:12px; font-weight:700; font-size:12px; background:%s20; color:%s; border:1px solid %s40;">%s</span></td>
                        <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:600; color:#0f172a;">%.2f</td>
                        <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:700; color:#004080;">%.2f</td>
                    </tr>
                """, idx++, code, title, cr, marks, totalMarks, gradeBadgeColor, gradeBadgeColor, gradeBadgeColor, grade, gp, crXgp));
            }
        } else {
            courseRowsHtml.append("""
                <tr>
                    <td colspan="8" style="text-align:center; padding:25px; color:#64748b;">No course exam results recorded for this semester.</td>
                </tr>
            """);
        }

        String studentName = res.getFullName() != null ? res.getFullName() : "N/A";
        String studentCode = res.getStudentId() != null ? res.getStudentId() : ("STU-" + studentId);
        String program = res.getProgramName() != null ? res.getProgramName() : "Undergraduate Program";
        String dept = res.getDepartmentName() != null ? res.getDepartmentName() : "General Studies";
        String faculty = res.getFacultyName() != null ? res.getFacultyName() : "Faculty of Science & Engineering";
        String session = res.getAcademicSessionName() != null ? res.getAcademicSessionName() : "2024-2025";
        String currentSemester = res.getSemester() != null ? res.getSemester() : "All Semesters";

        double semGpa = res.getSemesterGpa() != null ? res.getSemesterGpa() : 0.0;
        double cgpa = res.getCurrentCgpa() != null ? res.getCurrentCgpa() : 0.0;
        double regCr = res.getTotalRegisteredCredits() != null ? res.getTotalRegisteredCredits() : 0.0;
        double earnedCr = res.getTotalEarnedCredits() != null ? res.getTotalEarnedCredits() : 0.0;
        String status = res.getAcademicStatus() != null ? res.getAcademicStatus() : "PASS";

        String statusColor = "#16a34a";
        String statusBg = "#dcfce7";
        if ("FAIL".equalsIgnoreCase(status) || "PROBATION".equalsIgnoreCase(status)) {
            statusColor = "#dc2626";
            statusBg = "#fee2e2";
        } else if ("INCOMPLETE".equalsIgnoreCase(status)) {
            statusColor = "#d97706";
            statusBg = "#fef3c7";
        }

        String issueDate = java.time.LocalDate.now().toString();

        return String.format("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Academic Result Sheet - %s (%s)</title>
                <style>
                    * { margin:0; padding:0; box-sizing:border-box; font-family:'Segoe UI', Roboto, Helvetica, Arial, sans-serif; }
                    body { background:#f8fafc; color:#1e293b; padding:30px; display:flex; justify-content:center; }
                    .result-card { background:#fff; width:100%%; max-width:850px; padding:40px; border-radius:12px; box-shadow:0 4px 25px rgba(0,0,0,0.08); border:1px solid #e2e8f0; }
                    .header { text-align:center; border-bottom:2px solid #004080; padding-bottom:18px; margin-bottom:25px; position:relative; }
                    .uni-brand h1 { font-size:26px; color:#004080; font-weight:800; text-transform:uppercase; letter-spacing:1px; margin-bottom:3px; }
                    .uni-brand p { font-size:12px; color:#64748b; }
                    .report-title { font-size:16px; font-weight:800; color:#0f172a; text-transform:uppercase; letter-spacing:1.5px; margin-top:10px; display:inline-block; padding:4px 20px; background:#eff6ff; border:1px solid #bfdbfe; border-radius:30px; }
                    .student-grid { display:grid; grid-template-columns:1fr 1fr; gap:16px; background:#f8fafc; border:1px solid #e2e8f0; border-radius:10px; padding:18px; margin-bottom:22px; }
                    .info-group { font-size:13px; line-height:1.6; }
                    .info-group strong { color:#0f172a; }
                    .info-group span { color:#64748b; display:inline-block; width:120px; }
                    .summary-grid { display:grid; grid-template-columns:repeat(5, 1fr); gap:12px; margin-bottom:25px; }
                    .summary-box { background:#f8fafc; border:1px solid #e2e8f0; border-radius:8px; padding:12px; text-align:center; }
                    .summary-box.highlight { background:#eff6ff; border-color:#93c5fd; }
                    .sum-val { font-size:22px; font-weight:800; color:#004080; display:block; margin-bottom:2px; }
                    .sum-label { font-size:11px; text-transform:uppercase; color:#64748b; font-weight:700; letter-spacing:0.5px; }
                    table.courses-table { width:100%%; border-collapse:collapse; margin-bottom:25px; font-size:13px; }
                    table.courses-table th { background:#004080; color:#fff; padding:10px 12px; font-size:12px; font-weight:700; text-transform:uppercase; letter-spacing:0.5px; text-align:left; }
                    .grading-scale { display:grid; grid-template-columns:repeat(4, 1fr); gap:8px; background:#f8fafc; border:1px solid #e2e8f0; border-radius:8px; padding:10px; margin-bottom:25px; font-size:10.5px; color:#64748b; }
                    .grading-scale div { line-height:1.4; }
                    .grading-scale strong { color:#334155; }
                    .footer { border-top:1px solid #e2e8f0; padding-top:20px; display:flex; justify-content:space-between; align-items:flex-end; font-size:11px; color:#64748b; }
                    .sign-box { text-align:center; }
                    .sign-line { width:160px; border-top:1px dashed #94a3af; margin-top:40px; margin-bottom:6px; }
                    .print-btn-bar { margin-bottom:20px; text-align:right; }
                    .print-btn { background:#004080; color:#fff; border:none; padding:9px 20px; border-radius:6px; font-size:13px; font-weight:600; cursor:pointer; box-shadow:0 2px 8px rgba(0,64,128,0.2); }
                    .print-btn:hover { background:#002d5f; }
                    @media print {
                        body { background:#fff; padding:0; }
                        .result-card { box-shadow:none; border:none; padding:0; max-width:100%%; }
                        .print-btn-bar { display:none; }
                    }
                </style>
            </head>
            <body>
                <div style="width:100%%; max-width:850px;">
                    <div class="print-btn-bar">
                        <button class="print-btn" onclick="window.print()">🖨️ Print / Save Result Sheet as PDF</button>
                    </div>
                    <div class="result-card">
                        <div class="header">
                            <div class="uni-brand">
                                <h1>🏛️ Elite University</h1>
                                <p>Office of the Controller of Examinations | Academic Records Division</p>
                                <p>Dhaka, Bangladesh | exam.controller@eliteuniversity.edu</p>
                            </div>
                            <div class="report-title">OFFICIAL SEMESTER RESULT SHEET</div>
                        </div>

                        <div class="student-grid">
                            <div class="info-group">
                                <div><span>Student Name:</span> <strong>%s</strong></div>
                                <div><span>Student ID:</span> <strong style="color:#004080;">%s</strong></div>
                                <div><span>Degree Program:</span> <strong>%s</strong></div>
                                <div><span>Department:</span> %s</div>
                            </div>
                            <div class="info-group">
                                <div><span>Faculty:</span> %s</div>
                                <div><span>Academic Session:</span> %s</div>
                                <div><span>Semester Term:</span> <strong style="color:#004080;">%s</strong></div>
                                <div><span>Date of Issue:</span> %s</div>
                            </div>
                        </div>

                        <div class="summary-grid">
                            <div class="summary-box highlight">
                                <span class="sum-val">%.2f</span>
                                <span class="sum-label">Semester GPA</span>
                            </div>
                            <div class="summary-box highlight">
                                <span class="sum-val" style="color:#1e40af;">%.2f</span>
                                <span class="sum-label">Cumulative CGPA</span>
                            </div>
                            <div class="summary-box">
                                <span class="sum-val">%.1f</span>
                                <span class="sum-label">Credits Registered</span>
                            </div>
                            <div class="summary-box">
                                <span class="sum-val">%.1f</span>
                                <span class="sum-label">Credits Earned</span>
                            </div>
                            <div class="summary-box" style="background:%s; border-color:%s40;">
                                <span class="sum-val" style="color:%s; font-size:18px; margin-top:3px;">%s</span>
                                <span class="sum-label">Result Status</span>
                            </div>
                        </div>

                        <table class="courses-table">
                            <thead>
                                <tr>
                                    <th style="width:35px; text-align:center;">#</th>
                                    <th style="width:110px;">Course Code</th>
                                    <th>Course Title</th>
                                    <th style="width:60px; text-align:center;">Credits</th>
                                    <th style="width:90px; text-align:center;">Marks</th>
                                    <th style="width:70px; text-align:center;">Grade</th>
                                    <th style="width:60px; text-align:center;">GP</th>
                                    <th style="width:70px; text-align:right;">Cr × GP</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>

                        <div class="grading-scale">
                            <div><strong>Grading Policy:</strong><br>80%% - 100%% = A+ (4.00)<br>75%% - 79%% = A (3.75)</div>
                            <div><br>70%% - 74%% = A- (3.50)<br>65%% - 69%% = B+ (3.25)</div>
                            <div><br>60%% - 64%% = B (3.00)<br>55%% - 59%% = B- (2.75)</div>
                            <div><br>50%% - 54%% = C+ (2.50)<br>&lt; 40%% = F (0.00)</div>
                        </div>

                        <div class="footer">
                            <div class="sign-box">
                                <div class="sign-line"></div>
                                <span>Prepared By (Exam Officer)</span>
                            </div>
                            <div class="sign-box">
                                <div class="sign-line"></div>
                                <span>Verified By (Dean / Head)</span>
                            </div>
                            <div class="sign-box">
                                <div class="sign-line" style="border-top-style:solid; border-top-color:#004080;"></div>
                                <strong style="color:#004080;">Controller of Examinations</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
            </html>
        """,
        studentName, studentCode,
        studentName,
        studentCode,
        program,
        dept,
        faculty,
        session,
        currentSemester,
        issueDate,
        semGpa,
        cgpa,
        regCr,
        earnedCr,
        statusBg, statusColor, statusColor, status,
        courseRowsHtml.toString()
        );
    }
}
