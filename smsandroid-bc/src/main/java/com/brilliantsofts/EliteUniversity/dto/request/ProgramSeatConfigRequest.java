package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ProgramSeatConfigRequest {
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
}
