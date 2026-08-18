package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ApplicantRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantResponse;
import com.brilliantsofts.EliteUniversity.enums.ApplicationLevel;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;

import java.util.List;

public interface ApplicantService {
    ApplicantResponse create(ApplicantRequest request);
    ApplicantResponse update(Long id, ApplicantRequest request);
    ApplicantResponse getById(Long id);
    ApplicantResponse getByApplicationNumber(String applicationNumber);
    ApplicantResponse getByUserId(Long userId);
    List<ApplicantResponse> getAll();
    List<ApplicantResponse> getByLevel(ApplicationLevel level);
    List<ApplicantResponse> getByStatus(ApplicationStatus status);
    List<ApplicantResponse> getByProgram(Long programId);
    void delete(Long id);
}
