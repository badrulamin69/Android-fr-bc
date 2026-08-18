package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.HostelRequest;
import com.brilliantsofts.EliteUniversity.dto.response.HostelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HostelService {
    HostelResponse create(HostelRequest request);
    HostelResponse update(Long id, HostelRequest request);
    HostelResponse getById(Long id);
    Page<HostelResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
