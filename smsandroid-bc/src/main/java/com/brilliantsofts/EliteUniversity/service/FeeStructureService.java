package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.FeeStructureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeStructureResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeeStructureService {
    FeeStructureResponse create(FeeStructureRequest request);
    FeeStructureResponse update(Long id, FeeStructureRequest request);
    FeeStructureResponse getById(Long id);
    Page<FeeStructureResponse> getAll(Pageable pageable);
    List<FeeStructureResponse> getBySemesterAndProgram(Long semesterId, Long programId);
    void delete(Long id);
}
