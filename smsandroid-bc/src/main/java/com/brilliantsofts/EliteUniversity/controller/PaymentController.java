package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.PaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PaymentResponse;
import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import com.brilliantsofts.EliteUniversity.service.PaymentService;
import com.brilliantsofts.EliteUniversity.service.SSLCommerzService;
import com.brilliantsofts.EliteUniversity.dto.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;
    private final PaymentRepository paymentRepository;
    private final SSLCommerzService sslCommerzService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Page<PaymentResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(paymentRepository.findAll(pageable).map(PaymentMapper::toResponse));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Page<PaymentResponse>> search(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Payment> payments;
        if (status != null && !status.isEmpty()) {
            payments = paymentRepository.findByStatus(PaymentStatus.valueOf(status), pageable);
        } else {
            payments = paymentRepository.findAll(pageable);
        }
        return ResponseEntity.ok(payments.map(PaymentMapper::toResponse));
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPayments", paymentRepository.count());
        stats.put("successfulPayments", paymentRepository.countByStatus(PaymentStatus.SUCCESS));
        stats.put("pendingPayments", paymentRepository.countByStatus(PaymentStatus.PENDING));
        stats.put("failedPayments", paymentRepository.countByStatus(PaymentStatus.FAILED));
        stats.put("refundedPayments", paymentRepository.countByStatus(PaymentStatus.REFUNDED));
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER', 'STUDENT')")
    public ResponseEntity<Page<PaymentResponse>> getByStudent(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(paymentRepository.findByStudentId(studentId, pageable).map(PaymentMapper::toResponse));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PostMapping("/initiate")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER', 'STUDENT')")
    public ResponseEntity<Map<String, Object>> initiatePayment(@RequestBody Map<String, Object> data) {
        Payment payment = new Payment();
        payment.setAmount(((Number) data.get("amount")).doubleValue());
        Payment saved = paymentRepository.save(payment);
        Map<String, Object> result = sslCommerzService.initiatePayment(
                saved,
                (String) data.getOrDefault("customerName", "Customer"),
                (String) data.getOrDefault("customerEmail", ""),
                (String) data.getOrDefault("customerPhone", "")
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/process-online")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> processOnlinePayment(
            @PathVariable Long id,
            @RequestBody Map<String, String> data) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        String tranId = data.get("transactionId");
        if (tranId != null) {
            boolean valid = sslCommerzService.validateTransaction(tranId);
            payment.setStatus(valid ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
            payment.setTransactionId(tranId);
        }
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentRepository.save(payment)));
    }

    @PostMapping("/{id}/process-offline")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> processOfflinePayment(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentRepository.save(payment)));
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> approvePayment(
            @PathVariable Long id,
            @RequestParam(required = false) String approvedBy) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());
        if (approvedBy != null) payment.setCreatedBy(approvedBy);
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentRepository.save(payment)));
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> rejectPayment(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.FAILED);
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentRepository.save(payment)));
    }

    @PostMapping("/{id}/refund")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<Map<String, Object>> refundPayment(
            @PathVariable Long id,
            @RequestParam(required = false) String reason) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setNotes("Refunded: " + (reason != null ? reason : "No reason provided"));
        paymentRepository.save(payment);
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Payment refunded successfully");
        result.put("paymentId", id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER')")
    public ResponseEntity<PaymentResponse> update(@PathVariable Long id, @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/receipt")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'STAFF', 'ACCOUNTS_OFFICER', 'STUDENT', 'APPLICANT')")
    public ResponseEntity<byte[]> getPaymentReceipt(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        String html = generatePaymentReceiptHtml(payment);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_HTML);
        headers.setContentDisposition(org.springframework.http.ContentDisposition.inline()
                .filename("payment-receipt-" + (payment.getTransactionId() != null ? payment.getTransactionId() : payment.getId()) + ".html")
                .build());
        return ResponseEntity.ok().headers(headers).body(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @GetMapping("/receipt/transaction/{transactionId}")
    public ResponseEntity<byte[]> getPaymentReceiptByTransactionId(@PathVariable String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        String html = generatePaymentReceiptHtml(payment);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_HTML);
        headers.setContentDisposition(org.springframework.http.ContentDisposition.inline()
                .filename("receipt-" + transactionId + ".html")
                .build());
        return ResponseEntity.ok().headers(headers).body(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    private String generatePaymentReceiptHtml(Payment payment) {
        String status = payment.getStatus() != null ? payment.getStatus().name() : "SUCCESS";
        String statusColor = "#16a34a";
        String statusBg = "#dcfce7";
        if ("FAILED".equalsIgnoreCase(status)) {
            statusColor = "#dc2626";
            statusBg = "#fee2e2";
        } else if ("PENDING".equalsIgnoreCase(status)) {
            statusColor = "#e6a817";
            statusBg = "#fef3c7";
        }

        String studentInfo = "General University Payment";
        if (payment.getStudent() != null) {
            studentInfo = (payment.getStudent().getFullName() != null ? payment.getStudent().getFullName() : "Student") + 
                          " (ID: " + (payment.getStudent().getStudentId() != null ? payment.getStudent().getStudentId() : payment.getStudent().getId()) + ")";
        } else if (payment.getApplicant() != null) {
            studentInfo = (payment.getApplicant().getFullName() != null ? payment.getApplicant().getFullName() : "Applicant") +
                          " (App ID: " + (payment.getApplicant().getApplicationNumber() != null ? payment.getApplicant().getApplicationNumber() : payment.getApplicant().getId()) + ")";
        }

        String paymentDate = payment.getPaymentDate() != null ? payment.getPaymentDate().toString() : java.time.LocalDateTime.now().toString();
        String tranId = payment.getTransactionId() != null ? payment.getTransactionId() : "TXN-" + payment.getId();
        String method = payment.getPaymentMethod() != null ? payment.getPaymentMethod().name() : "Online / SSLCommerz";
        double amount = payment.getAmount() != null ? payment.getAmount() : 0.0;
        String notes = payment.getNotes() != null ? payment.getNotes() : "University Fee & Educational Services";

        return String.format("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Payment Receipt - %s</title>
                <style>
                    * { margin:0; padding:0; box-sizing:border-box; font-family:'Segoe UI', Roboto, Helvetica, Arial, sans-serif; }
                    body { background:#f8fafc; color:#1e293b; padding:30px; display:flex; justify-content:center; }
                    .receipt-card { background:#fff; width:100%%; max-width:700px; padding:35px; border-radius:12px; box-shadow:0 4px 20px rgba(0,0,0,0.08); border:1px solid #e2e8f0; }
                    .header { display:flex; justify-content:space-between; align-items:flex-start; border-bottom:2px solid #004080; padding-bottom:15px; margin-bottom:20px; }
                    .uni-brand h1 { font-size:22px; color:#004080; font-weight:800; text-transform:uppercase; }
                    .uni-brand p { font-size:11px; color:#64748b; margin-top:2px; }
                    .rec-meta { text-align:right; }
                    .rec-title { font-size:22px; font-weight:800; color:#0f172a; }
                    .status-badge { display:inline-block; margin-top:6px; padding:3px 12px; border-radius:20px; font-size:11px; font-weight:700; background:%s; color:%s; border:1px solid %s; }
                    .grid-box { display:grid; grid-template-columns:1fr 1fr; gap:15px; background:#f8fafc; padding:15px; border-radius:8px; margin-bottom:20px; border:1px solid #e2e8f0; }
                    .item-label { font-size:11px; text-transform:uppercase; color:#64748b; font-weight:600; }
                    .item-value { font-size:13px; font-weight:600; color:#0f172a; margin-top:2px; }
                    .amount-box { background:#eff6ff; border:2px solid #bfdbfe; border-radius:8px; padding:18px; text-align:center; margin-bottom:25px; }
                    .amount-label { font-size:13px; font-weight:700; color:#1e40af; text-transform:uppercase; letter-spacing:0.5px; }
                    .amount-value { font-size:32px; font-weight:800; color:#004080; margin-top:4px; }
                    .footer { border-top:1px solid #e2e8f0; padding-top:15px; display:flex; justify-content:space-between; align-items:center; font-size:11px; color:#64748b; }
                    .print-btn-bar { margin-bottom:15px; text-align:right; }
                    .print-btn { background:#004080; color:#fff; border:none; padding:8px 16px; border-radius:6px; font-size:12px; font-weight:600; cursor:pointer; }
                    @media print {
                        body { background:#fff; padding:0; }
                        .receipt-card { box-shadow:none; border:none; padding:0; }
                        .print-btn-bar { display:none; }
                    }
                </style>
            </head>
            <body>
                <div style="width:100%%; max-width:700px;">
                    <div class="print-btn-bar">
                        <button class="print-btn" onclick="window.print()">🖨️ Print / Save Receipt</button>
                    </div>
                    <div class="receipt-card">
                        <div class="header">
                            <div class="uni-brand">
                                <h1>🏛️ Elite University</h1>
                                <p>Official Payment & Fee Collection Receipt</p>
                                <p>Accounts Division | accounts@eliteuniversity.edu</p>
                            </div>
                            <div class="rec-meta">
                                <div class="rec-title">MONEY RECEIPT</div>
                                <div class="status-badge">%s</div>
                            </div>
                        </div>

                        <div class="grid-box">
                            <div>
                                <div class="item-label">Received From</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Transaction ID</div>
                                <div class="item-value" style="color:#004080;">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Payment Date</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Payment Method</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div style="grid-column:1/-1;">
                                <div class="item-label">Purpose / Description</div>
                                <div class="item-value">%s</div>
                            </div>
                        </div>

                        <div class="amount-box">
                            <div class="amount-label">Total Amount Paid</div>
                            <div class="amount-value">$%.2f</div>
                        </div>

                        <div class="footer">
                            <div>
                                <p>Status: <strong>VERIFIED & CLEARED</strong></p>
                                <p>Thank you for your payment to Elite University.</p>
                            </div>
                            <div style="text-align:center;">
                                <div style="width:130px; border-top:1px dashed #94a3af; margin-top:25px; margin-bottom:4px;"></div>
                                <span>Accounts Officer</span>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
            </html>
        """,
        tranId,
        statusBg, statusColor, statusColor,
        status,
        studentInfo,
        tranId,
        paymentDate,
        method,
        notes,
        amount
        );
    }
}
