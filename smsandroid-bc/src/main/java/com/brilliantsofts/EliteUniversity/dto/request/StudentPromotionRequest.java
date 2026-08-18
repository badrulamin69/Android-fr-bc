package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentPromotionRequest {
    private Long studentId;
    private Long fromSemesterId;
    private Long toSemesterId;
    private Long fromBatchId;
    private Long toBatchId;
    private LocalDate promotionDate;
    private String status;
    private String remarks;
    private Long approvedById;
}
