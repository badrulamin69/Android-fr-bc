package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.DiscountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DiscountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountService {
    DiscountResponse create(DiscountRequest request);
    DiscountResponse update(Long id, DiscountRequest request);
    DiscountResponse getById(Long id);
    Page<DiscountResponse> getAll(Pageable pageable);
    List<DiscountResponse> getByStudentId(Long studentId);
    void delete(Long id);
}
