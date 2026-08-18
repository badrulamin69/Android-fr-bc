package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ResultResponse;
import com.brilliantsofts.EliteUniversity.entity.ExamResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResultMapper {
    public static ExamResult toEntity(ResultRequest request) {
        if (request == null) return null;
        ExamResult entity = new ExamResult();
        entity.setTotalMarksObtained(request.getTotalMarksObtained());
        entity.setTotalMarks(request.getTotalMarks());
        entity.setPercentage(request.getPercentage());
        entity.setGrade(request.getGrade());
        entity.setResultStatus(request.getResultStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static ResultResponse toResponse(ExamResult entity) {
        if (entity == null) return null;
        ResultResponse response = new ResultResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setTotalMarksObtained(entity.getTotalMarksObtained());
        response.setMarksObtained(entity.getTotalMarksObtained());
        response.setTotalMarks(entity.getTotalMarks());
        response.setPercentage(entity.getPercentage());
        response.setGrade(entity.getGrade());
        response.setResultStatus(entity.getResultStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        // Grade & GPA calculation
        if (entity.getGrade() != null) {
            String g = entity.getGrade().toUpperCase();
            switch (g) {
                case "A+": response.setGpa(BigDecimal.valueOf(4.00)); break;
                case "A": response.setGpa(BigDecimal.valueOf(3.75)); break;
                case "A-": response.setGpa(BigDecimal.valueOf(3.50)); break;
                case "B+": response.setGpa(BigDecimal.valueOf(3.25)); break;
                case "B": response.setGpa(BigDecimal.valueOf(3.00)); break;
                case "B-": response.setGpa(BigDecimal.valueOf(2.75)); break;
                case "C+": response.setGpa(BigDecimal.valueOf(2.50)); break;
                case "C": response.setGpa(BigDecimal.valueOf(2.25)); break;
                case "D": response.setGpa(BigDecimal.valueOf(2.00)); break;
                case "F": response.setGpa(BigDecimal.valueOf(0.00)); break;
                default:
                    if (entity.getPercentage() != null) {
                        double pct = entity.getPercentage().doubleValue();
                        response.setGpa(BigDecimal.valueOf(Math.min(4.0, (pct / 100.0) * 4.0)).setScale(2, RoundingMode.HALF_UP));
                    }
            }
        }

        if (entity.getExam() != null) {
            response.setExamId(entity.getExam().getId());
            response.setExamName(entity.getExam().getName());
            if (entity.getExam().getCourse() != null) {
                response.setCourseId(entity.getExam().getCourse().getId());
                response.setCourseName(entity.getExam().getCourse().getCourseName());
                response.setCourseCode(entity.getExam().getCourse().getCourseCode());
            }
        }
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
            response.setStudentName(entity.getStudent().getFullName());
            response.setStudentCode(entity.getStudent().getStudentId());
            if (entity.getStudent().getProgram() != null) {
                response.setProgramName(entity.getStudent().getProgram().getName());
                if (entity.getStudent().getProgram().getDepartment() != null) {
                    response.setDepartmentName(entity.getStudent().getProgram().getDepartment().getName());
                }
            }
        }
        return response;
    }
}
