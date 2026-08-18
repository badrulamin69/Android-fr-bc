package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.InvoiceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.InvoiceResponse;
import com.brilliantsofts.EliteUniversity.dto.response.InvoiceItemResponse;
import com.brilliantsofts.EliteUniversity.entity.Invoice;
import com.brilliantsofts.EliteUniversity.entity.InvoiceItem;
import com.brilliantsofts.EliteUniversity.dto.mapper.InvoiceMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.InvoiceItemMapper;
import com.brilliantsofts.EliteUniversity.repository.InvoiceRepository;
import com.brilliantsofts.EliteUniversity.repository.InvoiceItemRepository;
import com.brilliantsofts.EliteUniversity.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceItemRepository itemRepository;

    @Autowired
    private com.brilliantsofts.EliteUniversity.repository.StudentRepository studentRepository;

    @Override
    public InvoiceResponse create(InvoiceRequest request) {
        Invoice entity = InvoiceMapper.toEntity(request);
        if (entity.getInvoiceNumber() == null) {
            entity.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        Invoice saved = repository.save(entity);
        if (request.getItems() != null) {
            for (var itemReq : request.getItems()) {
                InvoiceItem item = InvoiceItemMapper.toEntity(itemReq);
                item.setInvoiceId(saved.getId());
                itemRepository.save(item);
            }
        }
        return toResponseWithItems(saved);
    }

    @Override
    public InvoiceResponse update(Long id, InvoiceRequest request) {
        Invoice entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        entity.setInvoiceNumber(request.getInvoiceNumber());
        entity.setStudentId(request.getStudentId());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSemesterId(request.getSemesterId());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setPaidAmount(request.getPaidAmount());
        entity.setDueAmount(request.getDueAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setFineAmount(request.getFineAmount());
        entity.setStatus(request.getStatus());
        entity.setDueDate(request.getDueDate());
        entity.setNotes(request.getNotes());
        Invoice saved = repository.save(entity);
        if (request.getItems() != null) {
            List<InvoiceItem> existingItems = itemRepository.findByInvoiceId(id);
            itemRepository.deleteAll(existingItems);
            for (var itemReq : request.getItems()) {
                InvoiceItem item = InvoiceItemMapper.toEntity(itemReq);
                item.setInvoiceId(saved.getId());
                itemRepository.save(item);
            }
        }
        return toResponseWithItems(saved);
    }

    @Override
    public InvoiceResponse getById(Long id) {
        Invoice entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        return toResponseWithItems(entity);
    }

    @Override
    public Page<InvoiceResponse> getAll(Pageable pageable, String search) {
        return repository.findAll(pageable).map(e -> toResponseWithItems(e));
    }

    @Override
    public Page<InvoiceResponse> search(Pageable pageable, String search, String status) {
        if (status != null && !status.isEmpty()) {
            return repository.findByStatus(status).stream()
                    .map(this::toResponseWithItems)
                    .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
                    ));
        }
        return repository.findAll(pageable).map(this::toResponseWithItems);
    }

    @Override
    public Page<InvoiceResponse> getByStudentId(Pageable pageable, Long studentId) {
        List<Invoice> invoices = repository.findByStudentId(studentId);
        List<InvoiceResponse> responses = invoices.stream().map(this::toResponseWithItems).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), responses.size());
        List<InvoiceResponse> pageContent = start < responses.size() ? responses.subList(start, end) : List.of();
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, responses.size());
    }

    @Override
    public InvoiceResponse generate(Long studentId, Long semesterId, String academicYear) {
        Invoice entity = new Invoice();
        entity.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setStudentId(studentId);
        entity.setSemesterId(semesterId);
        entity.setAcademicYear(academicYear);
        entity.setStatus("PENDING");
        entity.setTotalAmount(0.0);
        entity.setPaidAmount(0.0);
        entity.setDueAmount(0.0);
        entity.setDiscountAmount(0.0);
        entity.setFineAmount(0.0);
        Invoice saved = repository.save(entity);
        return toResponseWithItems(saved);
    }

    @Override
    public InvoiceResponse updateStatus(Long id, String status) {
        Invoice entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        entity.setStatus(status);
        return toResponseWithItems(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        List<InvoiceItem> items = itemRepository.findByInvoiceId(id);
        itemRepository.deleteAll(items);
        repository.deleteById(id);
    }

    @Override
    public byte[] generateInvoicePdf(Long id) {
        String html = generateInvoiceHtml(id);
        return html.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    @Override
    public String generateInvoiceHtml(Long id) {
        Invoice invoice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        List<InvoiceItem> items = itemRepository.findByInvoiceId(id);

        String studentName = "Student";
        String studentCode = "N/A";
        String programName = "General Academic";
        if (invoice.getStudentId() != null) {
            var studentOpt = studentRepository.findById(invoice.getStudentId());
            if (studentOpt.isPresent()) {
                var s = studentOpt.get();
                studentName = s.getFullName() != null ? s.getFullName() : "Student #" + s.getId();
                studentCode = s.getStudentId() != null ? s.getStudentId() : "STU-" + s.getId();
                if (s.getProgram() != null && s.getProgram().getName() != null) {
                    programName = s.getProgram().getName();
                }
            }
        }

        StringBuilder itemRows = new StringBuilder();
        int idx = 1;
        for (InvoiceItem item : items) {
            String desc = item.getDescription() != null ? item.getDescription() : "Tuition / Academic Fee Item";
            double amt = item.getAmount() != null ? item.getAmount() : 0.0;
            double disc = item.getDiscountAmount() != null ? item.getDiscountAmount() : 0.0;
            double net = item.getNetAmount() != null ? item.getNetAmount() : (amt - disc);
            itemRows.append(String.format("""
                <tr>
                    <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#64748b;">%d</td>
                    <td style="padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:500; color:#1e293b;">%s</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#334155;">$%.2f</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#16a34a;">-$%.2f</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:600; color:#0f172a;">$%.2f</td>
                </tr>
            """, idx++, desc, amt, disc, net));
        }

        if (items.isEmpty()) {
            double total = invoice.getTotalAmount() != null ? invoice.getTotalAmount() : 0.0;
            itemRows.append(String.format("""
                <tr>
                    <td style="text-align:center; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#64748b;">1</td>
                    <td style="padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:500; color:#1e293b;">Semester Tuition & Educational Fees</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#334155;">$%.2f</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; color:#16a34a;">-$0.00</td>
                    <td style="text-align:right; padding:10px 12px; border-bottom:1px solid #e2e8f0; font-weight:600; color:#0f172a;">$%.2f</td>
                </tr>
            """, total));
        }

        double totalAmt = invoice.getTotalAmount() != null ? invoice.getTotalAmount() : 0.0;
        double paidAmt = invoice.getPaidAmount() != null ? invoice.getPaidAmount() : 0.0;
        double dueAmt = invoice.getDueAmount() != null ? invoice.getDueAmount() : (totalAmt - paidAmt);
        double discAmt = invoice.getDiscountAmount() != null ? invoice.getDiscountAmount() : 0.0;
        double fineAmt = invoice.getFineAmount() != null ? invoice.getFineAmount() : 0.0;

        String status = invoice.getStatus() != null ? invoice.getStatus().toUpperCase() : "PENDING";
        String statusColor = "#e6a817";
        String statusBg = "#fef3c7";
        if ("PAID".equalsIgnoreCase(status) || "SUCCESS".equalsIgnoreCase(status)) {
            statusColor = "#16a34a";
            statusBg = "#dcfce7";
        } else if ("OVERDUE".equalsIgnoreCase(status) || "FAILED".equalsIgnoreCase(status)) {
            statusColor = "#dc2626";
            statusBg = "#fee2e2";
        }

        String issueDate = invoice.getCreatedAt() != null ? invoice.getCreatedAt().toLocalDate().toString() : java.time.LocalDate.now().toString();
        String dueDate = invoice.getDueDate() != null ? invoice.getDueDate().toString() : java.time.LocalDate.now().plusDays(15).toString();

        return String.format("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Invoice - %s</title>
                <style>
                    * { margin:0; padding:0; box-sizing:border-box; font-family:'Segoe UI', Roboto, Helvetica, Arial, sans-serif; }
                    body { background:#f8fafc; color:#1e293b; padding:30px; display:flex; justify-content:center; }
                    .invoice-card { background:#fff; width:100%%; max-width:800px; padding:40px; border-radius:12px; box-shadow:0 4px 20px rgba(0,0,0,0.08); border:1px solid #e2e8f0; }
                    .header { display:flex; justify-content:space-between; align-items:flex-start; border-bottom:2px solid #004080; padding-bottom:20px; margin-bottom:25px; }
                    .uni-brand h1 { font-size:24px; color:#004080; font-weight:800; text-transform:uppercase; letter-spacing:0.5px; }
                    .uni-brand p { font-size:12px; color:#64748b; margin-top:3px; }
                    .inv-meta { text-align:right; }
                    .inv-title { font-size:26px; font-weight:800; color:#0f172a; letter-spacing:1px; }
                    .inv-no { font-size:14px; font-weight:600; color:#004080; margin-top:2px; }
                    .status-badge { display:inline-block; margin-top:8px; padding:4px 14px; border-radius:20px; font-size:12px; font-weight:700; background:%s; color:%s; border:1px solid %s; }
                    .info-grid { display:grid; grid-template-columns:1fr 1fr; gap:20px; margin-bottom:30px; }
                    .info-box h3 { font-size:12px; text-transform:uppercase; color:#64748b; letter-spacing:0.5px; margin-bottom:8px; }
                    .info-box p { font-size:14px; color:#1e293b; line-height:1.5; }
                    .info-box strong { color:#0f172a; }
                    table { width:100%%; border-collapse:collapse; margin-bottom:25px; }
                    th { background:#f1f5f9; padding:12px; font-size:12px; font-weight:700; text-transform:uppercase; color:#475569; text-align:left; border-bottom:2px solid #cbd5e1; }
                    .totals-section { display:flex; justify-content:flex-end; margin-bottom:30px; }
                    .totals-box { width:280px; }
                    .totals-row { display:flex; justify-content:space-between; padding:6px 0; font-size:13px; color:#475569; }
                    .totals-row.grand-total { border-top:2px solid #004080; margin-top:6px; padding-top:10px; font-size:16px; font-weight:800; color:#004080; }
                    .totals-row.due { color:#dc2626; font-weight:700; }
                    .totals-row.paid { color:#16a34a; font-weight:700; }
                    .footer { border-top:1px solid #e2e8f0; padding-top:20px; display:flex; justify-content:space-between; align-items:center; font-size:12px; color:#64748b; }
                    .signature-area { text-align:center; }
                    .sign-line { width:150px; border-top:1px dashed #94a3af; margin-top:35px; margin-bottom:5px; }
                    .print-btn-bar { margin-bottom:20px; text-align:right; }
                    .print-btn { background:#004080; color:#fff; border:none; padding:8px 18px; border-radius:6px; font-size:13px; font-weight:600; cursor:pointer; }
                    @media print {
                        body { background:#fff; padding:0; }
                        .invoice-card { box-shadow:none; border:none; padding:0; max-width:100%%; }
                        .print-btn-bar { display:none; }
                    }
                </style>
            </head>
            <body>
                <div style="width:100%%; max-width:800px;">
                    <div class="print-btn-bar">
                        <button class="print-btn" onclick="window.print()">🖨️ Print / Save as PDF</button>
                    </div>
                    <div class="invoice-card">
                        <div class="header">
                            <div class="uni-brand">
                                <h1>🏛️ Elite University</h1>
                                <p>Excellence in Education & Innovation</p>
                                <p>Accounts & Finance Division | info@eliteuniversity.edu</p>
                            </div>
                            <div class="inv-meta">
                                <div class="inv-title">INVOICE</div>
                                <div class="inv-no">#%s</div>
                                <div class="status-badge">%s</div>
                            </div>
                        </div>

                        <div class="info-grid">
                            <div class="info-box">
                                <h3>Billed To (Student)</h3>
                                <p><strong>%s</strong></p>
                                <p>Student ID: <strong>%s</strong></p>
                                <p>Program: %s</p>
                                <p>Academic Session: %s</p>
                            </div>
                            <div class="info-box" style="text-align:right;">
                                <h3>Invoice Details</h3>
                                <p>Invoice Date: <strong>%s</strong></p>
                                <p>Due Date: <strong>%s</strong></p>
                                <p>Semester ID: <strong>%s</strong></p>
                            </div>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th style="width:40px; text-align:center;">#</th>
                                    <th>Item & Description</th>
                                    <th style="text-align:right;">Amount</th>
                                    <th style="text-align:right;">Discount</th>
                                    <th style="text-align:right;">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>

                        <div class="totals-section">
                            <div class="totals-box">
                                <div class="totals-row"><span>Subtotal:</span><span>$%.2f</span></div>
                                <div class="totals-row"><span>Discount:</span><span style="color:#16a34a;">-$%.2f</span></div>
                                <div class="totals-row"><span>Late Fine:</span><span>+$%.2f</span></div>
                                <div class="totals-row grand-total"><span>Total Payable:</span><span>$%.2f</span></div>
                                <div class="totals-row paid"><span>Paid Amount:</span><span>$%.2f</span></div>
                                <div class="totals-row due"><span>Due Balance:</span><span>$%.2f</span></div>
                            </div>
                        </div>

                        <div class="footer">
                            <div>
                                <p><strong>Payment Instructions:</strong> Pay online via student portal or bank draft.</p>
                                <p>This is a computer-generated official university invoice.</p>
                            </div>
                            <div class="signature-area">
                                <div class="sign-line"></div>
                                <span>Authorized Signature</span>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
            </html>
        """,
        invoice.getInvoiceNumber() != null ? invoice.getInvoiceNumber() : ("INV-" + invoice.getId()),
        statusBg, statusColor, statusColor,
        invoice.getInvoiceNumber() != null ? invoice.getInvoiceNumber() : ("INV-" + invoice.getId()),
        status,
        studentName,
        studentCode,
        programName,
        invoice.getAcademicYear() != null ? invoice.getAcademicYear() : "2025-2026",
        issueDate,
        dueDate,
        invoice.getSemesterId() != null ? invoice.getSemesterId().toString() : "1",
        itemRows.toString(),
        totalAmt + discAmt - fineAmt,
        discAmt,
        fineAmt,
        totalAmt,
        paidAmt,
        dueAmt
        );
    }

    private InvoiceResponse toResponseWithItems(Invoice entity) {
        InvoiceResponse response = InvoiceMapper.toResponse(entity);
        if (entity.getStudentId() != null) {
            studentRepository.findById(entity.getStudentId()).ifPresent(s -> {
                response.setStudentName(s.getFullName() != null ? s.getFullName() : "Student #" + s.getId());
                response.setStudentCode(s.getStudentId() != null ? s.getStudentId() : "STU-" + s.getId());
            });
        }
        List<InvoiceItem> items = itemRepository.findByInvoiceId(entity.getId());
        response.setItems(items.stream().map(InvoiceItemMapper::toResponse).collect(Collectors.toList()));
        return response;
    }
}
