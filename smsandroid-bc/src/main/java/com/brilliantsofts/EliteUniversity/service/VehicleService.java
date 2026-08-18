package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.VehicleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    VehicleResponse create(VehicleRequest request);
    VehicleResponse update(Long id, VehicleRequest request);
    VehicleResponse getById(Long id);
    Page<VehicleResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
