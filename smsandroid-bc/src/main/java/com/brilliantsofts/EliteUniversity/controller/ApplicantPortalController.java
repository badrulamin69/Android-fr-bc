package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.ApplicantPortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/applicant-portal")
@RequiredArgsConstructor
public class ApplicantPortalController {
    private final ApplicantPortalService service;
    private final UserRepository userRepository;

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) return null;
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        return user != null ? user.getId() : null;
    }

    @GetMapping("/my-registration")
    public ResponseEntity<Object> getMyRegistration() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyRegistration(userId));
    }

    @GetMapping("/my-test")
    public ResponseEntity<Object> getMyTest() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyTest(userId));
    }

    @GetMapping("/my-test/{testId}/questions")
    public ResponseEntity<Object> getTestQuestions(@PathVariable Long testId) {
        return ResponseEntity.ok(service.getTestQuestions(testId));
    }

    @PostMapping("/test/{testId}/start")
    public ResponseEntity<Object> startTest(@PathVariable Long testId) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.startTest(userId, testId));
    }

    @PostMapping("/test/submit")
    public ResponseEntity<Object> submitTest(@RequestBody Map<String, Object> body) {
        Long userId = getCurrentUserId();
        Long attemptId = Long.valueOf(body.get("attemptId").toString());
        @SuppressWarnings("unchecked")
        Map<String, String> answers = (Map<String, String>) body.get("answers");
        return ResponseEntity.ok(service.submitTest(userId, attemptId, answers));
    }

    @GetMapping("/my-results")
    public ResponseEntity<Object> getMyResults() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyResults(userId));
    }

    @GetMapping("/my-allocation")
    public ResponseEntity<Object> getMyAllocation() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyAllocation(userId));
    }

    @PostMapping("/my-allocation/{id}/confirm")
    public ResponseEntity<Object> confirmAllocation(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.confirmAllocation(userId, id));
    }

    @PostMapping("/my-allocation/{id}/decline")
    public ResponseEntity<Object> declineAllocation(@PathVariable Long id) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.declineAllocation(userId, id));
    }

    @PostMapping("/my-enroll")
    public ResponseEntity<Object> enrollSelf() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.enrollSelf(userId));
    }

    @GetMapping("/my-enrollment")
    public ResponseEntity<Object> getMyEnrollment() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyEnrollment(userId));
    }

    @PostMapping("/my-admit")
    public ResponseEntity<Object> admitAsStudent() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.admitAsStudent(userId));
    }

    @PostMapping("/my-enroll-with-choice")
    public ResponseEntity<Object> enrollWithChoice(@RequestBody Map<String, Long> body) {
        Long userId = getCurrentUserId();
        Long facultyId = body.get("facultyId");
        Long departmentId = body.get("departmentId");
        Long programId = body.get("programId");
        return ResponseEntity.ok(service.enrollWithChoice(userId, facultyId, departmentId, programId));
    }

    @GetMapping(value = "/my-admit-card/pdf", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAdmitCardPdf() {
        Long userId = getCurrentUserId();
        byte[] pdfBytes = service.getAdmitCardPdf(userId);
        if (pdfBytes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"admit-card.pdf\"")
                .body(pdfBytes);
    }

    @GetMapping(value = "/my-admit-card/html", produces = org.springframework.http.MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getAdmitCardHtml() {
        Long userId = getCurrentUserId();
        String html = service.getAdmitCardHtml(userId);
        if (html == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(html);
    }

    @GetMapping("/my-merit")
    public ResponseEntity<Object> getMyMerit() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyMerit(userId));
    }

    @GetMapping("/my-waiting-position")
    public ResponseEntity<Object> getMyWaitingPosition() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyWaitingPosition(userId));
    }
}
