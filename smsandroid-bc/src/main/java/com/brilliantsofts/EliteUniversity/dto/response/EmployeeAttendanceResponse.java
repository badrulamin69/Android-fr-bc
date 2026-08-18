package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class EmployeeAttendanceResponse {
    private Long id;
    private String uniqueCode;
    private Long employeeId;
    private LocalDate attendanceDate;
    private String status;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
