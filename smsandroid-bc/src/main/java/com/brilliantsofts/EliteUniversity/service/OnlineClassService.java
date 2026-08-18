package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.OnlineClassRequest;
import com.brilliantsofts.EliteUniversity.dto.response.OnlineClassResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OnlineClassService {
    OnlineClassResponse create(OnlineClassRequest request);
    OnlineClassResponse update(Long id, OnlineClassRequest request);
    OnlineClassResponse getById(Long id);
    Page<OnlineClassResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
}
