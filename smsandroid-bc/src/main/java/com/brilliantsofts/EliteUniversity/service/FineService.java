package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.FineRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FineService {
    FineResponse create(FineRequest request);
    FineResponse update(Long id, FineRequest request);
    FineResponse getById(Long id);
    Page<FineResponse> getAll(Pageable pageable);
    List<FineResponse> getByStudentId(Long studentId);
    FineResponse waiveFine(Long id);
    void delete(Long id);
}
