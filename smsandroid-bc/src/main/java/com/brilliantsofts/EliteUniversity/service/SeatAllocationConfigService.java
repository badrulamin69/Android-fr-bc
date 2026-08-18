package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationConfigResponse;
import org.springframework.data.domain.Page;

public interface SeatAllocationConfigService {
    SeatAllocationConfigResponse create(SeatAllocationConfigRequest request);
    SeatAllocationConfigResponse update(Long id, SeatAllocationConfigRequest request);
    SeatAllocationConfigResponse getById(Long id);
    Page<SeatAllocationConfigResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    SeatAllocationConfigResponse activate(Long id);
    SeatAllocationConfigResponse close(Long id);
}
