package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class DepartmentRequest {
    private String name;
    private String code;
    private Long facultyId;
}
