package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class SemesterRegistrationRequest {
    private Long studentId;
    private Long semesterId;
    private Long batchId;
    private String status;
    private String remarks;
}
