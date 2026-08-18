package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LeaveRequestRequest {
    private Long employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status;
}
