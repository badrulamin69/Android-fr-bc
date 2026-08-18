package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProgramSeatConfigResponse {
    private Long id;
    private Long configId;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String shift;
    private Integer totalSeats;
    private Integer generalSeats;
    private Integer quotaSeats;
    private Integer reservedSeats;
    private Integer allocatedSeats;
    private Integer waitingSeats;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
