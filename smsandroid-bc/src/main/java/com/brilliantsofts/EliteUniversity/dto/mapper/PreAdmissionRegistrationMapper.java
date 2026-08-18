package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.response.PreAdmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;

public class PreAdmissionRegistrationMapper {

    public static PreAdmissionResponse toResponse(PreAdmissionRegistration entity) {
        if (entity == null) return null;
        PreAdmissionResponse response = new PreAdmissionResponse();
        response.setId(entity.getId());
        response.setRegistrationNumber(entity.getRegistrationNumber());
        response.setTrackingNumber(entity.getTrackingNumber());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setGender(entity.getGender());
        response.setBloodGroup(entity.getBloodGroup());
        response.setAddress(entity.getAddress());
        response.setFatherName(entity.getFatherName());
        response.setMotherName(entity.getMotherName());
        response.setGuardianPhone(entity.getGuardianPhone());
        response.setPhotoUrl(entity.getPhotoUrl());
        response.setSignatureUrl(entity.getSignatureUrl());
        response.setSscGpa(entity.getSscGpa());
        response.setSscYear(entity.getSscYear());
        response.setSscBoard(entity.getSscBoard());
        response.setHscGpa(entity.getHscGpa());
        response.setHscYear(entity.getHscYear());
        response.setHscBoard(entity.getHscBoard());
        response.setProgramPreference1(entity.getProgramPreference1());
        response.setProgramPreference2(entity.getProgramPreference2());
        response.setProgramPreference3(entity.getProgramPreference3());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setEmailVerified(entity.isEmailVerified());
        response.setSessionId(entity.getSessionId());
        response.setCircularId(entity.getCircularId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
