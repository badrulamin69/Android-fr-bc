package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.GradeRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.GradeRuleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GradeRuleService {
    GradeRuleResponse create(GradeRuleRequest request);
    GradeRuleResponse update(Long id, GradeRuleRequest request);
    GradeRuleResponse getById(Long id);
    Page<GradeRuleResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
