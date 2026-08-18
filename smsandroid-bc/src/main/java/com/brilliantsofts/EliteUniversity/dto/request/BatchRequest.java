package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class BatchRequest {
    private String name;
    private String code;
    private Integer startYear;
    private Integer endYear;
    private Long courseId;
}
