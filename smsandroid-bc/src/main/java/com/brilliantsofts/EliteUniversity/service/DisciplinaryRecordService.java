package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.DisciplinaryRecordRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DisciplinaryRecordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface DisciplinaryRecordService {
    DisciplinaryRecordResponse create(DisciplinaryRecordRequest request);
    DisciplinaryRecordResponse update(Long id, DisciplinaryRecordRequest request);
    DisciplinaryRecordResponse getById(Long id);
    Page<DisciplinaryRecordResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
