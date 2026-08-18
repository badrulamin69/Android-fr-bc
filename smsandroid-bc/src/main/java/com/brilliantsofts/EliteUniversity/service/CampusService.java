package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CampusRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CampusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampusService {
    CampusResponse create(CampusRequest request);
    CampusResponse update(Long id, CampusRequest request);
    CampusResponse getById(Long id);
    Page<CampusResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
