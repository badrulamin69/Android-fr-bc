package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatConfigResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProgramSeatConfigService {
    ProgramSeatConfigResponse create(ProgramSeatConfigRequest request);
    ProgramSeatConfigResponse update(Long id, ProgramSeatConfigRequest request);
    ProgramSeatConfigResponse getById(Long id);
    Page<ProgramSeatConfigResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    List<ProgramSeatConfigResponse> findByConfigId(Long configId);
    List<ProgramSeatConfigResponse> getAvailable(Long configId);
    Map<String, Object> getSummary(Long configId);
}
