package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.InvoiceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    InvoiceResponse create(InvoiceRequest request);
    InvoiceResponse update(Long id, InvoiceRequest request);
    InvoiceResponse getById(Long id);
    Page<InvoiceResponse> getAll(Pageable pageable, String search);
    Page<InvoiceResponse> search(Pageable pageable, String search, String status);
    Page<InvoiceResponse> getByStudentId(Pageable pageable, Long studentId);
    InvoiceResponse generate(Long studentId, Long semesterId, String academicYear);
    InvoiceResponse updateStatus(Long id, String status);
    void delete(Long id);
    byte[] generateInvoicePdf(Long id);
    String generateInvoiceHtml(Long id);
}
