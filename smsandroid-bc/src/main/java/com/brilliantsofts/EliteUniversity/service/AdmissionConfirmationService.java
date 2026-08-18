package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionConfirmationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionConfirmationResponse;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionDocumentResponse;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentSubmitRequest;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerifyRequest;
import com.brilliantsofts.EliteUniversity.dto.request.FeePaymentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AdmissionConfirmationService {
    AdmissionConfirmationResponse create(AdmissionConfirmationRequest request);
    AdmissionConfirmationResponse update(Long id, AdmissionConfirmationRequest request);
    AdmissionConfirmationResponse getById(Long id);
    Page<AdmissionConfirmationResponse> getAll(Pageable pageable, String search, String status, Boolean documentsVerified, Boolean feePaid);
    List<AdmissionConfirmationResponse> getMy(Long registrationId);
    AdmissionConfirmationResponse initiate(Long allocationId);
    AdmissionConfirmationResponse submitDocuments(Long confirmationId, List<DocumentSubmitRequest> documents);
    AdmissionConfirmationResponse verifyDocuments(Long confirmationId, DocumentVerifyRequest request);
    AdmissionConfirmationResponse payFee(Long confirmationId, FeePaymentRequest request);
    AdmissionConfirmationResponse confirm(Long confirmationId);
    List<AdmissionDocumentResponse> getDocuments(Long confirmationId);
    Map<String, Object> getStats();
    void delete(Long id);
}
