package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentConfigResponse;

import java.util.List;

public interface EnrollmentConfigService {
    EnrollmentConfigResponse create(EnrollmentConfigRequest request);
    EnrollmentConfigResponse update(Long id, EnrollmentConfigRequest request);
    EnrollmentConfigResponse getById(Long id);
    List<EnrollmentConfigResponse> getAll();
    List<EnrollmentConfigResponse> getActive();
    EnrollmentConfigResponse getBySemester(Long semesterId);
    boolean isEnrollmentOpen(Long semesterId);
    EnrollmentConfigResponse closeEnrollment(Long id);
    EnrollmentConfigResponse reopenEnrollment(Long id);
    void delete(Long id);
}
