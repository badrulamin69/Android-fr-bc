package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicCalendarRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicCalendarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademicCalendarService {
    AcademicCalendarResponse create(AcademicCalendarRequest request);
    AcademicCalendarResponse update(Long id, AcademicCalendarRequest request);
    AcademicCalendarResponse getById(Long id);
    Page<AcademicCalendarResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
