package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.EligibilityVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityVerificationResponse;
import com.brilliantsofts.EliteUniversity.entity.EligibilityVerification;
import java.time.LocalDateTime;

public class EligibilityVerificationMapper {
    public static EligibilityVerification toEntity(EligibilityVerificationRequest request) {
        EligibilityVerification entity = new EligibilityVerification();
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        entity.setStatus(request.getStatus());
        entity.setVerifiedBy(request.getVerifiedBy());
        entity.setRemarks(request.getRemarks());
        entity.setSscGpaVerified(request.isSscGpaVerified());
        entity.setHscGpaVerified(request.isHscGpaVerified());
        entity.setDocumentsVerified(request.isDocumentsVerified());
        return entity;
    }

    public static EligibilityVerificationResponse toResponse(EligibilityVerification entity) {
        EligibilityVerificationResponse response = new EligibilityVerificationResponse();
        response.setId(entity.getId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setTestId(entity.getTestId());
        response.setStatus(entity.getStatus());
        response.setVerifiedBy(entity.getVerifiedBy());
        response.setVerifiedAt(entity.getVerifiedAt());
        response.setRemarks(entity.getRemarks());
        response.setSscGpaVerified(entity.isSscGpaVerified());
        response.setHscGpaVerified(entity.isHscGpaVerified());
        response.setDocumentsVerified(entity.isDocumentsVerified());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}