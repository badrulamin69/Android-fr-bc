package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AlumniRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AlumniResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AlumniService {
    AlumniResponse create(AlumniRequest request);
    AlumniResponse update(Long id, AlumniRequest request);
    AlumniResponse getById(Long id);
    Page<AlumniResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
