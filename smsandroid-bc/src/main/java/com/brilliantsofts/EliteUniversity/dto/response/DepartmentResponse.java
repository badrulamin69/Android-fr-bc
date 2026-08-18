package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private String code;
    private Long facultyId;
    private String facultyName;
}
