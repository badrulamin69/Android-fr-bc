package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransportAllocationRequest {
    private Long studentId;
    private Long routeId;
    private Long vehicleId;
    private String pickupPoint;
    private String dropPoint;
    private BigDecimal monthlyFee;
    private String status;
}
