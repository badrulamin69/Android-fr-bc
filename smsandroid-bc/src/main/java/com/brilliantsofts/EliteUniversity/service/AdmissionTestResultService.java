package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResultResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantTestResultView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmissionTestResultService {
    AdmissionTestResultResponse create(AdmissionTestResultRequest request);
    AdmissionTestResultResponse update(Long id, AdmissionTestResultRequest request);
    AdmissionTestResultResponse getById(Long id);
    Page<AdmissionTestResultResponse> getAll(Pageable pageable, String search);
    Page<ApplicantTestResultView> getApplicantsWithResults(Pageable pageable, String search);
    void delete(Long id);
    List<AdmissionTestResultResponse> bulkSave(List<AdmissionTestResultRequest> requests);
}
