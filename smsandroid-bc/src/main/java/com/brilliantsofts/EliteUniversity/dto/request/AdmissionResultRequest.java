package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AdmissionResultRequest {
    private Double admissionScore;
    private Integer meritPosition;
    private String resultStatus;
    private LocalDate resultDate;
    private Long applicantId;
    private Long programId;
}
