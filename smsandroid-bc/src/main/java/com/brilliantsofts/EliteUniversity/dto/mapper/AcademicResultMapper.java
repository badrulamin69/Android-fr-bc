package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicResultResponse;
import com.brilliantsofts.EliteUniversity.entity.AcademicResult;

public class AcademicResultMapper {
    public static AcademicResult toEntity(AcademicResultRequest request) {
        AcademicResult entity = new AcademicResult();
        entity.setExamType(request.getExamType());
        entity.setBoard(request.getBoard());
        entity.setInstitutionName(request.getInstitutionName());
        entity.setRollNumber(request.getRollNumber());
        entity.setRegistrationNumber(request.getRegistrationNumber());
        entity.setPassingYear(request.getPassingYear());
        entity.setGpa(request.getGpa());
        entity.setResultDocument(request.getResultDocument());
        return entity;
    }

    public static AcademicResultResponse toResponse(AcademicResult entity) {
        AcademicResultResponse response = new AcademicResultResponse();
        response.setId(entity.getId());
        response.setExamType(entity.getExamType());
        response.setBoard(entity.getBoard());
        response.setInstitutionName(entity.getInstitutionName());
        response.setRollNumber(entity.getRollNumber());
        response.setRegistrationNumber(entity.getRegistrationNumber());
        response.setPassingYear(entity.getPassingYear());
        response.setGpa(entity.getGpa());
        response.setResultDocument(entity.getResultDocument());
        if (entity.getApplicant() != null) {
            response.setApplicantId(entity.getApplicant().getId());
            response.setApplicantName(entity.getApplicant().getFullName());
        }
        return response;
    }
}
