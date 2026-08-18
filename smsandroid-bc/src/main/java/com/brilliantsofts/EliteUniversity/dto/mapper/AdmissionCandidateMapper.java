package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCandidateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCandidateResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionCandidate;

public class AdmissionCandidateMapper {
    public static AdmissionCandidate toEntity(AdmissionCandidateRequest request) {
        AdmissionCandidate entity = new AdmissionCandidate();
        entity.setUniqueCode(request.getUniqueCode());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setGender(request.getGender());
        entity.setAddress(request.getAddress());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setStatus(request.getStatus());
        entity.setAppliedCourseId(request.getAppliedCourseId());
        return entity;
    }

    public static AdmissionCandidateResponse toResponse(AdmissionCandidate entity) {
        AdmissionCandidateResponse response = new AdmissionCandidateResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setGender(entity.getGender());
        response.setAddress(entity.getAddress());
        response.setApplicationNumber(entity.getApplicationNumber());
        response.setStatus(entity.getStatus());
        response.setAppliedCourseId(entity.getAppliedCourseId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
