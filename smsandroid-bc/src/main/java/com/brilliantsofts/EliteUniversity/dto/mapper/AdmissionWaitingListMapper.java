package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingList;

public class AdmissionWaitingListMapper {
    public static AdmissionWaitingList toEntity(AdmissionWaitingListRequest request) {
        AdmissionWaitingList entity = new AdmissionWaitingList();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTestId(request.getTestId());
        entity.setStatus(request.getStatus());
        entity.setTotalSlots(request.getTotalSlots());
        entity.setTotalApplicants(request.getTotalApplicants());
        entity.setCutoffScore(request.getCutoffScore());
        entity.setPublishedAt(request.getPublishedAt());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AdmissionWaitingListResponse toResponse(AdmissionWaitingList entity) {
        AdmissionWaitingListResponse response = new AdmissionWaitingListResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setAcademicYear(entity.getAcademicYear());
        response.setSessionId(entity.getSessionId());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setProgramId(entity.getProgramId());
        response.setShift(entity.getShift());
        response.setTestId(entity.getTestId());
        response.setStatus(entity.getStatus());
        response.setTotalSlots(entity.getTotalSlots());
        response.setTotalApplicants(entity.getTotalApplicants());
        response.setCutoffScore(entity.getCutoffScore());
        response.setPublishedAt(entity.getPublishedAt());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
