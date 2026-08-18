package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListEntryResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritListEntry;

public class AdmissionMeritListEntryMapper {
    public static AdmissionMeritListEntry toEntity(AdmissionMeritListEntryRequest request) {
        AdmissionMeritListEntry entity = new AdmissionMeritListEntry();
        entity.setMeritListId(request.getMeritListId());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setRank(request.getRank());
        entity.setRollNumber(request.getRollNumber());
        entity.setApplicationNumber(request.getApplicationNumber());
        entity.setApplicantName(request.getApplicantName());
        entity.setFacultyName(request.getFacultyName());
        entity.setDepartmentName(request.getDepartmentName());
        entity.setProgramName(request.getProgramName());
        entity.setShift(request.getShift());
        entity.setTestMarks(request.getTestMarks());
        entity.setTestMaxMarks(request.getTestMaxMarks());
        entity.setScore(request.getScore());
        entity.setAcademicScore(request.getAcademicScore());
        entity.setTotalWeightedScore(request.getTotalWeightedScore());
        entity.setSscGpa(request.getSscGpa());
        entity.setHscGpa(request.getHscGpa());
        entity.setQuotaType(request.getQuotaType());
        entity.setStatus(request.getStatus());
        entity.setIsOffered(request.getIsOffered());
        entity.setIsEnrolled(request.getIsEnrolled());
        entity.setRemarks(request.getRemarks());
        entity.setSubmittedAt(request.getSubmittedAt());
        return entity;
    }

    public static AdmissionMeritListEntryResponse toResponse(AdmissionMeritListEntry entity) {
        AdmissionMeritListEntryResponse response = new AdmissionMeritListEntryResponse();
        response.setId(entity.getId());
        response.setMeritListId(entity.getMeritListId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setRank(entity.getRank());
        response.setRollNumber(entity.getRollNumber());
        response.setApplicationNumber(entity.getApplicationNumber());
        response.setApplicantName(entity.getApplicantName());
        response.setFacultyName(entity.getFacultyName());
        response.setDepartmentName(entity.getDepartmentName());
        response.setProgramName(entity.getProgramName());
        response.setShift(entity.getShift());
        response.setTestMarks(entity.getTestMarks());
        response.setTestMaxMarks(entity.getTestMaxMarks());
        response.setScore(entity.getScore());
        response.setAcademicScore(entity.getAcademicScore());
        response.setTotalWeightedScore(entity.getTotalWeightedScore());
        response.setSscGpa(entity.getSscGpa());
        response.setHscGpa(entity.getHscGpa());
        response.setQuotaType(entity.getQuotaType());
        response.setStatus(entity.getStatus());
        response.setIsOffered(entity.getIsOffered());
        response.setIsEnrolled(entity.getIsEnrolled());
        response.setRemarks(entity.getRemarks());
        response.setSubmittedAt(entity.getSubmittedAt());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
