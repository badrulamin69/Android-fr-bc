package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class ProgramResponse {
    private Long id;
    private String name;
    private String code;
    private Integer durationYears;
    private Integer totalCredits;
    private Long departmentId;
    private String departmentName;
}
