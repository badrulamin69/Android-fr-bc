package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionTestQuestionRequest {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String correctOption;
    private Integer marks;
    private Double negativeMarks;
    private Long testId;
    private String subject;
    private String difficulty;
    private String explanation;
    private String questionType;
    private Boolean isActive;
}
