package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentAttendanceRequest {
    private Long studentId;
    private Long courseId;
    private Long semesterId;
    private LocalDate attendanceDate;
    private String status;
    private String remarks;
}
