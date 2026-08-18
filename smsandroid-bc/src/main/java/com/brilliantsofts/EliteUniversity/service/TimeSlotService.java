package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.TimeSlotRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TimeSlotResponse;

import java.util.List;

public interface TimeSlotService {
    TimeSlotResponse create(TimeSlotRequest request);
    TimeSlotResponse update(Long id, TimeSlotRequest request);
    TimeSlotResponse getById(Long id);
    List<TimeSlotResponse> getAll();
    void delete(Long id);
}
