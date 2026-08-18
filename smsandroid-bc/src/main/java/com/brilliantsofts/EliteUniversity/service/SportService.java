package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SportRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SportService {
    SportResponse create(SportRequest request);
    SportResponse update(Long id, SportRequest request);
    SportResponse getById(Long id);
    Page<SportResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
