package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TranscriptRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TranscriptResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TranscriptService {
    TranscriptResponse create(TranscriptRequest request);
    TranscriptResponse update(Long id, TranscriptRequest request);
    TranscriptResponse getById(Long id);
    Page<TranscriptResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
