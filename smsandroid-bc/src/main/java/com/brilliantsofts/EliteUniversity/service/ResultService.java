package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResultService {
    ResultResponse create(ResultRequest request);
    ResultResponse update(Long id, ResultRequest request);
    ResultResponse getById(Long id);
    Page<ResultResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
