package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.PayrollRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PayrollResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PayrollService {
    PayrollResponse create(PayrollRequest request);
    PayrollResponse update(Long id, PayrollRequest request);
    PayrollResponse getById(Long id);
    Page<PayrollResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}