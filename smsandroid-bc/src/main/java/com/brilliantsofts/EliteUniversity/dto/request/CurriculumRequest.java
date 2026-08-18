package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CurriculumRequest {
    private Long programId;
    private Long subjectId;
    private Long semesterId;
    private boolean isRequired;
    private Integer orderNo;
    private Integer creditHours;
}
