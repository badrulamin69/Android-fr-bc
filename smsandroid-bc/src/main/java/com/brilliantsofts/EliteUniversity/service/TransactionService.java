package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TransactionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    TransactionResponse update(Long id, TransactionRequest request);
    TransactionResponse getById(Long id);
    Page<TransactionResponse> getAll(Pageable pageable);
    void delete(Long id);
}
