package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AdministrationResponse {
    private Long id;
    private String uniqueCode;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
