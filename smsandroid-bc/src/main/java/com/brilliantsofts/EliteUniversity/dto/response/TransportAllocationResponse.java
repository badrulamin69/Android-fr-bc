package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransportAllocationResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long routeId;
    private Long vehicleId;
    private String pickupPoint;
    private String dropPoint;
    private BigDecimal monthlyFee;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
