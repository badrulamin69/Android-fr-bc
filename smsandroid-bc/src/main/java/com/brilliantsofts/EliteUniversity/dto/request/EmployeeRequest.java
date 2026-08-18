package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String employeeId;
    private String fullName;
    private String phone;
    private String designation;
    private EmployeeType employeeType;
    private Long userId;
    private Long departmentId;
}
