package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
    private String phone;
    private String designation;
    private EmployeeType employeeType;
    private String status;
    private Long userId;
    private Long departmentId;
    private String departmentName;
}
