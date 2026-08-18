package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.RoomRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomResponse create(RoomRequest request);
    RoomResponse update(Long id, RoomRequest request);
    RoomResponse getById(Long id);
    Page<RoomResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
