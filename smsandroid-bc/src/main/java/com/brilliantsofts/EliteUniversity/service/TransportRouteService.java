package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TransportRouteRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TransportRouteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransportRouteService {
    TransportRouteResponse create(TransportRouteRequest request);
    TransportRouteResponse update(Long id, TransportRouteRequest request);
    TransportRouteResponse getById(Long id);
    Page<TransportRouteResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
