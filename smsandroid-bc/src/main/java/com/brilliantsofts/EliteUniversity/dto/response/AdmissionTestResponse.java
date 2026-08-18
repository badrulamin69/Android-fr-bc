package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionTestResponse {
    private Long id;
    private String name;
    private String academicYear;
    private Long sessionId;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String shift;
    private String testType;
    private LocalDateTime testDate;
    private String startTime;
    private String endTime;
    private Integer durationMinutes;
    private Integer totalMarks;
    private Integer passingMarks;
    private Boolean negativeMarking;
    private Double negativeMarkValue;
    private String examCenter;
    private String building;
    private String room;
    private Integer seatCapacity;
    private String instructions;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
