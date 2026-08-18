package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AdmissionResultResponse {
    private Long id;
    private Double admissionScore;
    private Integer meritPosition;
    private String resultStatus;
    private LocalDate resultDate;
    private Long applicantId;
    private String applicantName;
    private Long programId;
    private String programName;
}
