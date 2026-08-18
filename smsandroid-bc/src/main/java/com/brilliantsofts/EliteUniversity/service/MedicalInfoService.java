package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.MedicalInfoRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MedicalInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface MedicalInfoService {
    MedicalInfoResponse create(MedicalInfoRequest request);
    MedicalInfoResponse update(Long id, MedicalInfoRequest request);
    MedicalInfoResponse getById(Long id);
    Page<MedicalInfoResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
