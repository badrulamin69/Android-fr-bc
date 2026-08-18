package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InvoiceRequest {
    private String invoiceNumber;
    private Long studentId;
    private String academicYear;
    private Long semesterId;
    private Double totalAmount;
    private Double paidAmount;
    private Double dueAmount;
    private Double discountAmount;
    private Double fineAmount;
    private String status;
    private LocalDate dueDate;
    private String notes;
    private List<InvoiceItemRequest> items;
}
