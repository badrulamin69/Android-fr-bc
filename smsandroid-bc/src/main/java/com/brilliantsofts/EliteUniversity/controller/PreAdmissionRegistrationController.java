package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionRegisterResponse;
import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionStatusResponse;
import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import com.brilliantsofts.EliteUniversity.service.PreAdmissionRegistrationService;
import com.brilliantsofts.EliteUniversity.service.SSLCommerzService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PreAdmissionRegistrationController {

    private final PreAdmissionRegistrationService service;
    private final PaymentRepository paymentRepository;
    private final SSLCommerzService sslCommerzService;

    // ==================== PUBLIC ENDPOINTS ====================

    @PostMapping("/pre-admission/register")
    public ResponseEntity<PreAdmissionRegisterResponse> register(
            @RequestParam Map<String, String> fields,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "signature", required = false) MultipartFile signature) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(fields, photo, signature));
    }

    @GetMapping("/pre-admission/status/{registrationNumber}")
    public ResponseEntity<PreAdmissionStatusResponse> checkStatus(@PathVariable String registrationNumber) {
        return ResponseEntity.ok(service.checkStatus(registrationNumber));
    }

    @GetMapping("/pre-admission/register/{registrationNumber}/pdf")
    public ResponseEntity<byte[]> getRegistrationPdf(@PathVariable String registrationNumber) {
        byte[] pdfBytes = service.getRegistrationPdf(registrationNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "registration-" + registrationNumber + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @GetMapping("/pre-admission/register/{registrationNumber}/qr-code")
    public ResponseEntity<byte[]> getRegistrationQrCode(@PathVariable String registrationNumber) {
        byte[] qrBytes = service.getRegistrationQrCode(registrationNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(headers).body(qrBytes);
    }

    @PostMapping("/pre-admission/register/{registrationNumber}/pay")
    public ResponseEntity<Map<String, Object>> initiatePayment(
            @PathVariable String registrationNumber,
            @RequestBody Map<String, Object> body) {
        PreAdmissionRegistration registration = service.getByRegistrationNumber(registrationNumber);
        if (registration == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Registration not found"));
        }

        Double amount = 500.0;
        if (body != null && body.containsKey("amount") && body.get("amount") != null) {
            try {
                double parsed = Double.parseDouble(body.get("amount").toString());
                if (parsed > 0) {
                    amount = parsed;
                }
            } catch (Exception ignored) {}
        }
        String customerName = registration.getFirstName() + " " + registration.getLastName();
        String customerEmail = registration.getEmail();
        String customerPhone = registration.getPhone() != null ? registration.getPhone() : "";

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);
        Payment saved = paymentRepository.save(payment);

        registration.setPaymentId(saved.getId());
        service.updatePaymentId(registration.getId(), saved.getId());

        Map<String, Object> result = sslCommerzService.initiatePayment(saved, customerName, customerEmail, customerPhone);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pre-admission/register/{registrationNumber}/payment-status")
    public ResponseEntity<Map<String, Object>> getPaymentStatus(@PathVariable String registrationNumber) {
        PreAdmissionRegistration registration = service.getByRegistrationNumber(registrationNumber);
        if (registration == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Registration not found"));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("registrationNumber", registrationNumber);
        result.put("paymentId", registration.getPaymentId());

        if (registration.getPaymentId() != null) {
            Payment payment = paymentRepository.findById(registration.getPaymentId()).orElse(null);
            if (payment != null) {
                result.put("paymentStatus", payment.getStatus().name());
                result.put("paymentNumber", payment.getPaymentNumber());
                result.put("amount", payment.getAmount());
            } else {
                result.put("paymentStatus", "NOT_FOUND");
            }
        } else {
            result.put("paymentStatus", "NO_PAYMENT");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/pre-admission/register/payment-status/{tranId}")
    public ResponseEntity<Map<String, Object>> getPaymentStatusByTranId(@PathVariable String tranId) {
        Payment payment = paymentRepository.findByTransactionId(tranId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("paymentId", payment.getId());
        result.put("paymentStatus", payment.getStatus().name());
        result.put("paymentNumber", payment.getTransactionId());
        result.put("amount", payment.getAmount());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pre-admission/register/{registrationNumber}/payment-invoice")
    public ResponseEntity<byte[]> getPreAdmissionPaymentInvoice(@PathVariable String registrationNumber) {
        PreAdmissionRegistration registration = service.getByRegistrationNumber(registrationNumber);
        if (registration == null) {
            return ResponseEntity.notFound().build();
        }

        Payment payment = null;
        if (registration.getPaymentId() != null) {
            payment = paymentRepository.findById(registration.getPaymentId()).orElse(null);
        }

        double amount = payment != null && payment.getAmount() != null ? payment.getAmount() : 500.0;
        String status = payment != null && payment.getStatus() != null ? payment.getStatus().name() : "PAID";
        String tranId = payment != null && payment.getTransactionId() != null ? payment.getTransactionId() : "TRX-" + registration.getRegistrationNumber();
        String dateStr = payment != null && payment.getPaymentDate() != null ? payment.getPaymentDate().toString() : java.time.LocalDateTime.now().toString();

        String statusColor = "#16a34a";
        String statusBg = "#dcfce7";
        if ("FAILED".equalsIgnoreCase(status)) {
            statusColor = "#dc2626";
            statusBg = "#fee2e2";
        } else if ("PENDING".equalsIgnoreCase(status)) {
            statusColor = "#e6a817";
            statusBg = "#fef3c7";
        }

        String html = String.format("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Pre-Admission Payment Invoice - %s</title>
                <style>
                    * { margin:0; padding:0; box-sizing:border-box; font-family:'Segoe UI', Roboto, sans-serif; }
                    body { background:#f8fafc; color:#1e293b; padding:30px; display:flex; justify-content:center; }
                    .invoice-card { background:#fff; width:100%%; max-width:700px; padding:35px; border-radius:12px; box-shadow:0 4px 20px rgba(0,0,0,0.08); border:1px solid #e2e8f0; }
                    .header { display:flex; justify-content:space-between; align-items:flex-start; border-bottom:2px solid #004080; padding-bottom:15px; margin-bottom:20px; }
                    .uni-brand h1 { font-size:22px; color:#004080; font-weight:800; text-transform:uppercase; }
                    .uni-brand p { font-size:11px; color:#64748b; margin-top:2px; }
                    .rec-meta { text-align:right; }
                    .rec-title { font-size:20px; font-weight:800; color:#0f172a; }
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
                        .invoice-card { box-shadow:none; border:none; padding:0; }
                        .print-btn-bar { display:none; }
                    }
                </style>
            </head>
            <body>
                <div style="width:100%%; max-width:700px;">
                    <div class="print-btn-bar">
                        <button class="print-btn" onclick="window.print()">🖨️ Print / Save Payment Invoice</button>
                    </div>
                    <div class="invoice-card">
                        <div class="header">
                            <div class="uni-brand">
                                <h1>🏛️ Elite University</h1>
                                <p>Admission & Pre-Registration Fee Receipt</p>
                                <p>Admissions Directorate | admission@eliteuniversity.edu</p>
                            </div>
                            <div class="rec-meta">
                                <div class="rec-title">PAYMENT INVOICE</div>
                                <div class="status-badge">%s</div>
                            </div>
                        </div>

                        <div class="grid-box">
                            <div>
                                <div class="item-label">Applicant Name</div>
                                <div class="item-value">%s %s</div>
                            </div>
                            <div>
                                <div class="item-label">Registration No</div>
                                <div class="item-value" style="color:#004080;">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Tracking Number</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Transaction ID</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Program Preference</div>
                                <div class="item-value">%s</div>
                            </div>
                            <div>
                                <div class="item-label">Payment Date</div>
                                <div class="item-value">%s</div>
                            </div>
                        </div>

                        <div class="amount-box">
                            <div class="amount-label">Pre-Admission Application Fee</div>
                            <div class="amount-value">$%.2f</div>
                        </div>

                        <div class="footer">
                            <div>
                                <p>Status: <strong>PAYMENT VERIFIED</strong></p>
                                <p>Please preserve this receipt for admission test and verification.</p>
                            </div>
                            <div style="text-align:center;">
                                <div style="width:130px; border-top:1px dashed #94a3af; margin-top:25px; margin-bottom:4px;"></div>
                                <span>Admission Officer</span>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
            </html>
        """,
        registration.getRegistrationNumber(),
        statusBg, statusColor, statusColor,
        status,
        registration.getFirstName(), registration.getLastName() != null ? registration.getLastName() : "",
        registration.getRegistrationNumber(),
        registration.getTrackingNumber() != null ? registration.getTrackingNumber() : "N/A",
        tranId,
        registration.getProgramPreference1() != null ? registration.getProgramPreference1() : "Undergraduate Program",
        dateStr,
        amount
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setContentDisposition(org.springframework.http.ContentDisposition.inline()
                .filename("payment-invoice-" + registration.getRegistrationNumber() + ".html")
                .build());
        return ResponseEntity.ok().headers(headers).body(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    // ==================== ADMIN ENDPOINTS ====================

    @GetMapping("/pre-admissions")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Page<PreAdmissionRegistration>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/pre-admissions/stats")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @GetMapping("/pre-admissions/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<PreAdmissionRegistration> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/pre-admissions/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<PreAdmissionRegistration> update(
            @PathVariable Long id,
            @RequestBody PreAdmissionRegistration data) {
        return ResponseEntity.ok(service.update(id, data));
    }

    @DeleteMapping("/pre-admissions/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pre-admissions/{id}/approve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<PreAdmissionRegistration> approve(@PathVariable Long id) {
        return ResponseEntity.ok(service.approve(id));
    }

    @PutMapping("/pre-admissions/{id}/reject")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<PreAdmissionRegistration> reject(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.reject(id, body.get("remarks")));
    }

    @PostMapping("/pre-admissions/process-merit")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> processMerit() {
        return ResponseEntity.ok(service.processMerit());
    }

    @GetMapping("/pre-admissions/merit-preview")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<List<PreAdmissionRegistration>> getMeritPreview() {
        return ResponseEntity.ok(service.getMeritPreview());
    }

    @GetMapping("/pre-admissions/{id}/admit-card")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<String> getAdmitCard(@PathVariable Long id) {
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(service.getAdmitCard(id));
    }

    @GetMapping("/pre-admissions/{id}/admit-card/pdf")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<byte[]> getAdmitCardPdf(@PathVariable Long id) {
        byte[] pdfBytes = service.getAdmitCardPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "admit-card-" + id + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
