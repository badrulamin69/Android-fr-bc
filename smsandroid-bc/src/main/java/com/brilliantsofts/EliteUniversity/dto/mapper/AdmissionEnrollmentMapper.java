package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionEnrollment;

public class AdmissionEnrollmentMapper {
    public static AdmissionEnrollment toEntity(AdmissionEnrollmentRequest request) {
        AdmissionEnrollment entity = new AdmissionEnrollment();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setEnrollmentNumber(request.getEnrollmentNumber());
        entity.setApplicationId(request.getApplicationId());
        entity.setStudentId(request.getStudentId());
        entity.setOfferLetterId(request.getOfferLetterId());
        entity.setProgramId(request.getProgramId());
        entity.setSemesterId(request.getSemesterId());
        entity.setBatchId(request.getBatchId());
        entity.setSectionId(request.getSectionId());
        entity.setStatus(request.getStatus());
        entity.setEnrolledAt(request.getEnrolledAt());
        entity.setRemarks(request.getRemarks());
        entity.setIsDocumentVerified(request.getIsDocumentVerified());
        entity.setIsFeePaid(request.getIsFeePaid());
        entity.setTotalFeePaid(request.getTotalFeePaid());
        entity.setEnrolledById(request.getEnrolledById());
        return entity;
    }

    public static AdmissionEnrollmentResponse toResponse(AdmissionEnrollment entity) {
        AdmissionEnrollmentResponse response = new AdmissionEnrollmentResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setEnrollmentNumber(entity.getEnrollmentNumber());
        response.setApplicationId(entity.getApplicationId());
        response.setStudentId(entity.getStudentId());
        response.setOfferLetterId(entity.getOfferLetterId());
        response.setProgramId(entity.getProgramId());
        response.setSemesterId(entity.getSemesterId());
        response.setBatchId(entity.getBatchId());
        response.setSectionId(entity.getSectionId());
        response.setStatus(entity.getStatus());
        response.setEnrolledAt(entity.getEnrolledAt());
        response.setRemarks(entity.getRemarks());
        response.setIsDocumentVerified(entity.getIsDocumentVerified());
        response.setIsFeePaid(entity.getIsFeePaid());
        response.setTotalFeePaid(entity.getTotalFeePaid());
        response.setEnrolledById(entity.getEnrolledById());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
