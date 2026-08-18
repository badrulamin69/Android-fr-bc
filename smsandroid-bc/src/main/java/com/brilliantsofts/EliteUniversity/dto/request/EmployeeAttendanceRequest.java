package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EmployeeAttendanceRequest {
    private Long employeeId;
    private LocalDate attendanceDate;
    private String status;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String remarks;
}
