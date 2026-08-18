package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HostelAllocationRequest {
    private Long studentId;
    private Long roomId;
    private LocalDate allocationDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal monthlyRent;
}
