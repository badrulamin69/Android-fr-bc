package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.PaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PaymentResponse;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {
    PaymentResponse create(PaymentRequest request);
    PaymentResponse update(Long id, PaymentRequest request);
    PaymentResponse getById(Long id);
    PaymentResponse getByTransactionId(String transactionId);
    List<PaymentResponse> getAll();
    List<PaymentResponse> getByApplicant(Long applicantId);
    List<PaymentResponse> getByStudent(Long studentId);
    List<PaymentResponse> getByStatus(PaymentStatus status);
    void delete(Long id);
}
