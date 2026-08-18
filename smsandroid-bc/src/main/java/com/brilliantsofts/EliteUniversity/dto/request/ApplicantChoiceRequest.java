package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ApplicantChoiceRequest {
    private Long submissionId;
    private Integer priority;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String facultyName;
    private String departmentName;
    private String programName;
    private String shift;
    private String status;
}
