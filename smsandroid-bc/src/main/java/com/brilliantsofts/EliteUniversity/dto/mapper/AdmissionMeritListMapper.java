package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritList;

public class AdmissionMeritListMapper {
    public static AdmissionMeritList toEntity(AdmissionMeritListRequest request) {
        AdmissionMeritList entity = new AdmissionMeritList();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setSessionId(request.getSessionId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setQuotaType(request.getQuotaType());
        entity.setTestId(request.getTestId());
        entity.setCircularId(request.getCircularId());
        entity.setStatus(request.getStatus());
        entity.setTotalSeats(request.getTotalSeats());
        entity.setTotalApplicants(request.getTotalApplicants());
        entity.setSelectedCount(request.getSelectedCount());
        entity.setWaitingCount(request.getWaitingCount());
        entity.setCutoffScore(request.getCutoffScore());
        entity.setPublishedAt(request.getPublishedAt());
        entity.setPublishedBy(request.getPublishedBy());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static AdmissionMeritListResponse toResponse(AdmissionMeritList entity) {
        if (entity == null) return null;
        AdmissionMeritListResponse response = new AdmissionMeritListResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setAcademicYear(entity.getAcademicYear());
        response.setSessionId(entity.getSessionId());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setProgramId(entity.getProgramId());
        response.setShift(entity.getShift());
        response.setQuotaType(entity.getQuotaType());
        response.setTestId(entity.getTestId());
        response.setCircularId(entity.getCircularId());
        response.setStatus(entity.getStatus());
        response.setTotalSeats(entity.getTotalSeats());
        response.setTotalApplicants(entity.getTotalApplicants());
        response.setSelectedCount(entity.getSelectedCount());
        response.setWaitingCount(entity.getWaitingCount());
        response.setCutoffScore(entity.getCutoffScore());
        response.setPublishedAt(entity.getPublishedAt());
        response.setPublishedBy(entity.getPublishedBy());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
