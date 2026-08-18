package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SeatAllocationService {
    SeatAllocationResponse create(SeatAllocationRequest request);
    SeatAllocationResponse update(Long id, SeatAllocationRequest request);
    SeatAllocationResponse getById(Long id);
    Page<SeatAllocationResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    List<SeatAllocationResponse> findByTestId(Long testId);
    List<SeatAllocationResponse> autoGenerate(Long testId);
}
