package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CreditRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CreditRuleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreditRuleService {
    CreditRuleResponse create(CreditRuleRequest request);
    CreditRuleResponse update(Long id, CreditRuleRequest request);
    CreditRuleResponse getById(Long id);
    Page<CreditRuleResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
