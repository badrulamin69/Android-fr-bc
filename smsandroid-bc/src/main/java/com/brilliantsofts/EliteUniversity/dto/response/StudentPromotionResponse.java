package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentPromotionResponse {
    private Long id;
    private String uniqueCode;
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
