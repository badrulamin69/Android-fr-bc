package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransportRouteRequest {
    private String name;
    private String routeCode;
    private String startPoint;
    private String endPoint;
    private BigDecimal distanceKm;
    private BigDecimal fare;
    private Boolean isActive;
}
