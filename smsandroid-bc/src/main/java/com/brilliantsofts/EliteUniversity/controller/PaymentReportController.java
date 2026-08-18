package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment-reports")
@RequiredArgsConstructor
public class PaymentReportController {

    private final PaymentRepository paymentRepository;

    @GetMapping("/daily")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> getDailyReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate reportDate = date != null ? date : LocalDate.now();
        LocalDateTime startOfDay = reportDate.atStartOfDay();
        LocalDateTime endOfDay = reportDate.atTime(LocalTime.MAX);

        Map<String, Object> report = new HashMap<>();
        report.put("date", reportDate.toString());
        report.put("totalTransactions", paymentRepository.count());
        report.put("successfulPayments", paymentRepository.countByStatus(PaymentStatus.SUCCESS));
        report.put("pendingPayments", paymentRepository.countByStatus(PaymentStatus.PENDING));
        report.put("failedPayments", paymentRepository.countByStatus(PaymentStatus.FAILED));
        return ResponseEntity.ok(report);
    }

    @GetMapping("/monthly")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> getMonthlyReport(
            @RequestParam int month,
            @RequestParam int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        Map<String, Object> report = new HashMap<>();
        report.put("month", yearMonth.toString());
        report.put("totalTransactions", paymentRepository.count());
        report.put("successfulPayments", paymentRepository.countByStatus(PaymentStatus.SUCCESS));
        report.put("pendingPayments", paymentRepository.countByStatus(PaymentStatus.PENDING));
        report.put("failedPayments", paymentRepository.countByStatus(PaymentStatus.FAILED));
        report.put("refundedPayments", paymentRepository.countByStatus(PaymentStatus.REFUNDED));
        return ResponseEntity.ok(report);
    }

    @GetMapping("/yearly")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> getYearlyReport(@RequestParam int year) {
        Map<String, Object> report = new HashMap<>();
        report.put("year", year);
        report.put("totalTransactions", paymentRepository.count());
        report.put("successfulPayments", paymentRepository.countByStatus(PaymentStatus.SUCCESS));
        report.put("pendingPayments", paymentRepository.countByStatus(PaymentStatus.PENDING));
        report.put("failedPayments", paymentRepository.countByStatus(PaymentStatus.FAILED));
        report.put("refundedPayments", paymentRepository.countByStatus(PaymentStatus.REFUNDED));
        return ResponseEntity.ok(report);
    }

    @GetMapping("/analytics")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalTransactions", paymentRepository.count());
        analytics.put("successfulPayments", paymentRepository.countByStatus(PaymentStatus.SUCCESS));
        analytics.put("pendingPayments", paymentRepository.countByStatus(PaymentStatus.PENDING));
        analytics.put("failedPayments", paymentRepository.countByStatus(PaymentStatus.FAILED));
        analytics.put("refundedPayments", paymentRepository.countByStatus(PaymentStatus.REFUNDED));
        return ResponseEntity.ok(analytics);
    }
}
