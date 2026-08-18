package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AdministrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String employeeCode;
    private String qualification;
    private String specialization;
    private LocalDate joiningDate;
    private String status;
    private Long userId;
    private Long departmentId;
}
