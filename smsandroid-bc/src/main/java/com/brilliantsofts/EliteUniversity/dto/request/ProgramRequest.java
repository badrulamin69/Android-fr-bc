package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ProgramRequest {
    private String name;
    private String code;
    private Integer durationYears;
    private Integer totalCredits;
    private Long departmentId;
}
