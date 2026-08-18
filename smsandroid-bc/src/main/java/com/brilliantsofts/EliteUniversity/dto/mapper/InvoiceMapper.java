package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.InvoiceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.InvoiceResponse;
import com.brilliantsofts.EliteUniversity.entity.Invoice;

public class InvoiceMapper {
    public static Invoice toEntity(InvoiceRequest request) {
        Invoice entity = new Invoice();
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
        return entity;
    }

    public static InvoiceResponse toResponse(Invoice entity) {
        InvoiceResponse response = new InvoiceResponse();
        response.setId(entity.getId());
        response.setInvoiceNumber(entity.getInvoiceNumber());
        response.setStudentId(entity.getStudentId());
        response.setAcademicYear(entity.getAcademicYear());
        response.setSemesterId(entity.getSemesterId());
        response.setTotalAmount(entity.getTotalAmount());
        response.setPaidAmount(entity.getPaidAmount());
        response.setDueAmount(entity.getDueAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setFineAmount(entity.getFineAmount());
        response.setStatus(entity.getStatus());
        response.setDueDate(entity.getDueDate());
        response.setNotes(entity.getNotes());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
