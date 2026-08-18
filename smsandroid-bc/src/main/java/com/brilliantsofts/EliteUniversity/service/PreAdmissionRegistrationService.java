package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionRegisterResponse;
import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionStatusResponse;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PreAdmissionRegistrationService {

    PreAdmissionRegisterResponse register(Map<String, String> fields, MultipartFile photo, MultipartFile signature);

    PreAdmissionStatusResponse checkStatus(String registrationNumber);

    PreAdmissionRegistration getById(Long id);

    Page<PreAdmissionRegistration> getAll(int page, int size, String sortBy, String sortDir, String search);

    PreAdmissionRegistration update(Long id, PreAdmissionRegistration data);

    void delete(Long id);

    PreAdmissionRegistration approve(Long id);

    PreAdmissionRegistration reject(Long id, String remarks);

    Map<String, Object> processMerit();

    List<PreAdmissionRegistration> getMeritPreview();

    String getAdmitCard(Long id);

    byte[] getAdmitCardPdf(Long id);

    byte[] getRegistrationPdf(String registrationNumber);

    byte[] getRegistrationQrCode(String registrationNumber);

    Map<String, Object> getStats();

    PreAdmissionRegistration getByRegistrationNumber(String registrationNumber);

    void updatePaymentId(Long id, Long paymentId);
}
