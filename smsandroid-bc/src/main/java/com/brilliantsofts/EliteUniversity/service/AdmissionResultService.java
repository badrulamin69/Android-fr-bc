package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionResultResponse;

import java.util.List;

public interface AdmissionResultService {
    AdmissionResultResponse create(AdmissionResultRequest request);
    AdmissionResultResponse update(Long id, AdmissionResultRequest request);
    AdmissionResultResponse getById(Long id);
    AdmissionResultResponse getByApplicant(Long applicantId);
    List<AdmissionResultResponse> getAll();
    List<AdmissionResultResponse> getByProgram(Long programId);
    List<AdmissionResultResponse> getByStatus(String status);
    List<AdmissionResultResponse> getMeritList(Long programId);
    void delete(Long id);
}
