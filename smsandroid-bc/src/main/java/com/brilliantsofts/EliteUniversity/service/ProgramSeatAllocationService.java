package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatAllocationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;

public interface ProgramSeatAllocationService {
    ProgramSeatAllocationResponse create(ProgramSeatAllocationRequest request);
    ProgramSeatAllocationResponse getById(Long id);
    Page<ProgramSeatAllocationResponse> getAll(String search, Long configId, Pageable pageable);
    Map<String, Object> getStats(Long configId);
    ProgramSeatAllocationResponse manualAllocate(Long registrationId, Long programId, Long configId, String shift, String remarks);
    ProgramSeatAllocationResponse changeAllocation(Long id, Long newProgramId, String shift, String remarks);
    ProgramSeatAllocationResponse cancelAllocation(Long id, String remarks);
    ProgramSeatAllocationResponse acceptAllocation(Long id);
    ProgramSeatAllocationResponse declineAllocation(Long id, String remarks);
    Map<String, Object> runAutoAllocation(Long configId);
    void delete(Long id);
}
