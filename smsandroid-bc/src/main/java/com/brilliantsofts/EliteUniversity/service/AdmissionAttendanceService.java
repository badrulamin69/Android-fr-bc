package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionAttendanceResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AdmissionAttendanceService {
    AdmissionAttendanceResponse create(AdmissionAttendanceRequest request);
    AdmissionAttendanceResponse update(Long id, AdmissionAttendanceRequest request);
    AdmissionAttendanceResponse getById(Long id);
    Page<AdmissionAttendanceResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    List<AdmissionAttendanceResponse> findByTestId(Long testId);
    Map<String, Object> getStatsByTestId(Long testId);
    AdmissionAttendanceResponse markAttendance(AdmissionAttendanceRequest request);
}
