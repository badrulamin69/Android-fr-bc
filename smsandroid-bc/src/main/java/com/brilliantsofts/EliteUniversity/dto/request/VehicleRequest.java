package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class VehicleRequest {
    private String vehicleNumber;
    private String vehicleType;
    private Integer capacity;
    private String driverName;
    private String driverPhone;
    private Boolean isActive;
}
