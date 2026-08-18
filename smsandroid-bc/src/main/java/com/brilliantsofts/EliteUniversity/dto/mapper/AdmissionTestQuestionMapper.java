package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestQuestionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestQuestionResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmissionTestQuestion;

public class AdmissionTestQuestionMapper {
    public static AdmissionTestQuestion toEntity(AdmissionTestQuestionRequest request) {
        AdmissionTestQuestion entity = new AdmissionTestQuestion();
        entity.setQuestionText(request.getQuestionText());
        entity.setOptionA(request.getOptionA());
        entity.setOptionB(request.getOptionB());
        entity.setOptionC(request.getOptionC());
        entity.setOptionD(request.getOptionD());
        entity.setOptionE(request.getOptionE());
        entity.setCorrectOption(request.getCorrectOption());
        entity.setMarks(request.getMarks());
        entity.setNegativeMarks(request.getNegativeMarks());
        entity.setTestId(request.getTestId());
        entity.setSubject(request.getSubject());
        entity.setDifficulty(request.getDifficulty());
        entity.setExplanation(request.getExplanation());
        entity.setQuestionType(request.getQuestionType());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static AdmissionTestQuestionResponse toResponse(AdmissionTestQuestion entity) {
        AdmissionTestQuestionResponse response = new AdmissionTestQuestionResponse();
        response.setId(entity.getId());
        response.setQuestionText(entity.getQuestionText());
        response.setOptionA(entity.getOptionA());
        response.setOptionB(entity.getOptionB());
        response.setOptionC(entity.getOptionC());
        response.setOptionD(entity.getOptionD());
        response.setOptionE(entity.getOptionE());
        response.setCorrectOption(entity.getCorrectOption());
        response.setMarks(entity.getMarks());
        response.setNegativeMarks(entity.getNegativeMarks());
        response.setTestId(entity.getTestId());
        response.setSubject(entity.getSubject());
        response.setDifficulty(entity.getDifficulty());
        response.setExplanation(entity.getExplanation());
        response.setQuestionType(entity.getQuestionType());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
