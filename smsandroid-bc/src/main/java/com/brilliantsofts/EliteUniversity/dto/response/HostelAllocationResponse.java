package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HostelAllocationResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long roomId;
    private LocalDate allocationDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal monthlyRent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
