package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.MarkRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MarkResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarkService {
    MarkResponse create(MarkRequest request);
    MarkResponse update(Long id, MarkRequest request);
    MarkResponse getById(Long id);
    Page<MarkResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
