package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AnnouncementRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AnnouncementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {
    AnnouncementResponse create(AnnouncementRequest request);
    AnnouncementResponse update(Long id, AnnouncementRequest request);
    AnnouncementResponse getById(Long id);
    Page<AnnouncementResponse> getAll(Pageable pageable);
    void delete(Long id);
}
