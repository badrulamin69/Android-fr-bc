package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransportRouteResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String routeCode;
    private String startPoint;
    private String endPoint;
    private BigDecimal distanceKm;
    private BigDecimal fare;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
