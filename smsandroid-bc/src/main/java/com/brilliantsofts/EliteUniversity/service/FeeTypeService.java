package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.FeeTypeRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeeTypeService {
    FeeTypeResponse create(FeeTypeRequest request);
    FeeTypeResponse update(Long id, FeeTypeRequest request);
    FeeTypeResponse getById(Long id);
    Page<FeeTypeResponse> getAll(Pageable pageable);
    List<FeeTypeResponse> getActive();
    List<FeeTypeResponse> getByCategory(String category);
    void delete(Long id);
}
