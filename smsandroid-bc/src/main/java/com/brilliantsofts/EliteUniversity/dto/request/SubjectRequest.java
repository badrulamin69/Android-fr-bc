package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class SubjectRequest {
    private String name;
    private String code;
    private Integer credits;
    private Long courseId;
    private Long departmentId;
}
