package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.BuildingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.BuildingResponse;

import java.util.List;

public interface BuildingService {
    BuildingResponse create(BuildingRequest request);
    BuildingResponse update(Long id, BuildingRequest request);
    BuildingResponse getById(Long id);
    List<BuildingResponse> getAll();
    void delete(Long id);
}
